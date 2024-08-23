package com.mysettlement.video.service;

import com.mysettlement.user.entity.User;
import com.mysettlement.user.entity.UserRole;
import com.mysettlement.user.exception.NoUserFoundException;
import com.mysettlement.user.repository.UserRepository;
import com.mysettlement.video.entity.Video;
import com.mysettlement.video.exception.UploaderRoleRequiredException;
import com.mysettlement.video.repository.VideoRepository;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.mysettlement.video.entity.VideoStatus.AVAILABLE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final UserRepository userRepository;

    @Transactional
    public VideoResponseDto uploadVideo(VideoUploadRequestDto videoUploadRequestDto) {
        // 유저 가입 여부 확인
        User foundUser = userRepository.findByEmail(videoUploadRequestDto.getUserEmail()).orElseThrow(NoUserFoundException::new);
        // 유저 자격 확인
        if (isNotUploader(foundUser)) {
            throw new UploaderRoleRequiredException();
        }

        Video newVideo = Video.builder()
                .videoTitle(videoUploadRequestDto.getTitle())
                .user(foundUser)
                .videoDesc(videoUploadRequestDto.getDesc())
                .videoStatus(AVAILABLE)
                .videoView(0)
                .videoPricePerView(1.0)
                .build();
        videoRepository.save(newVideo);
        return VideoResponseDto.of(newVideo);
    }

    private boolean isNotUploader(User user) {
        return user.getUserRole() != UserRole.UPLOADER;
    }
}
