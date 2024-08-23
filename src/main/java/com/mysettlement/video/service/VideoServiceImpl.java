package com.mysettlement.video.service;

import com.mysettlement.user.entity.User;
import com.mysettlement.user.entity.UserRole;
import com.mysettlement.user.exception.NoUserFoundException;
import com.mysettlement.user.repository.UserRepository;
import com.mysettlement.video.entity.Video;
import com.mysettlement.video.exception.InvalidVideoUpdateRequestException;
import com.mysettlement.video.exception.NoVideoFoundException;
import com.mysettlement.video.exception.UploaderRoleRequiredException;
import com.mysettlement.video.repository.VideoRepository;
import com.mysettlement.video.request.VideoStatusChangeRequestDto;
import com.mysettlement.video.request.VideoUpdateRequestDto;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mysettlement.video.entity.VideoStatus.AVAILABLE;
import static com.mysettlement.video.entity.VideoStatus.isValidStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public VideoResponseDto uploadVideo(VideoUploadRequestDto videoUploadRequestDto) {
        // 유저 가입 여부 확인
        User foundUser = userRepository.findByEmail(videoUploadRequestDto.userEmail()).orElseThrow(NoUserFoundException::new);
        // 유저 자격 확인
        if (isNotUploader(foundUser)) {
            throw new UploaderRoleRequiredException();
        }

        Video newVideo = Video.builder()
                .videoTitle(videoUploadRequestDto.title())
                .user(foundUser)
                .videoDesc(videoUploadRequestDto.desc())
                .videoStatus(AVAILABLE)
                .videoView(0)
                .videoPricePerView(1.0)
                .build();
        videoRepository.save(newVideo);
        return VideoResponseDto.of(newVideo);
    }

    @Override
    @Transactional
    public VideoResponseDto chageStatus(Long videoId, VideoStatusChangeRequestDto videoStatusChangeRequestDto) {
        if(!isValidStatus(videoStatusChangeRequestDto.videoStatus()))
            throw new InvalidVideoUpdateRequestException();
        Video foundVideo = videoRepository.findById(videoId).orElseThrow(NoVideoFoundException::new);
        foundVideo.update(videoStatusChangeRequestDto);
        return VideoResponseDto.of(foundVideo);
    }

    @Override
    @Transactional
    public VideoResponseDto update(Long videoId, VideoUpdateRequestDto videoUpdateRequestDto) {
        // 유저 조회
        User foundUser = userRepository.findByEmail(videoUpdateRequestDto.email()).orElseThrow(NoUserFoundException::new);

        // 비디오 확인
        Video foundVideo = videoRepository.findById(videoId).orElseThrow(NoVideoFoundException::new);

        // 주인 확인
        if(foundUser.getId() != foundVideo.getUser().getId()) throw new InvalidVideoUpdateRequestException();

        foundVideo.update(videoUpdateRequestDto);
        return VideoResponseDto.of(foundVideo);
    }

    private boolean isNotUploader(User user) {
        return user.getUserRole() != UserRole.UPLOADER;
    }
}
