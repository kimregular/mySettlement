package com.mysettlement.domain.viewHistory.service;

import com.mysettlement.domain.user.entity.User;
import com.mysettlement.domain.user.exception.NoUserFoundException;
import com.mysettlement.domain.user.repository.UserRepository;
import com.mysettlement.domain.video.entity.Video;
import com.mysettlement.domain.video.exception.NoVideoFoundException;
import com.mysettlement.domain.video.repository.VideoRepository;
import com.mysettlement.domain.viewHistory.dto.request.ViewVideoRequestDto;
import com.mysettlement.domain.viewHistory.entity.ViewHistory;
import com.mysettlement.domain.viewHistory.repository.ViewHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ViewHistoryServiceImpl {

    private final ViewHistoryRepository viewHistoryRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public ViewHistory viewVideo(Long videoId, ViewVideoRequestDto viewVideoRequestDto) {
        User foundUser = userRepository.findById(viewVideoRequestDto.userId()).orElseThrow(NoUserFoundException::new);
        Video foundVideo = videoRepository.findById(videoId).orElseThrow(NoVideoFoundException::new);
        boolean hasViewHistory = hasViewHistory(foundUser, foundVideo);
        ViewHistory viewHistory = recordViewHistory(foundUser, foundVideo, viewVideoRequestDto.viewDate(), hasViewHistory);
        return viewHistoryRepository.save(viewHistory);
    }

    private ViewHistory recordViewHistory(User user, Video video, LocalDateTime viewDate, boolean hasViewHistory) {
        if (!hasViewHistory) {
            log.info("ViewHistory has been updated!");
            video.viewUpdate();
        }
        return ViewHistory.builder().user(user).video(video).viewDate(viewDate).build();
    }

    private boolean hasViewHistory(User user, Video video) {
        return viewHistoryRepository.findByUserIdAndVideoId(user.getId(), video.getId()).isPresent();
    }
}
