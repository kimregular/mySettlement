package com.mysettlement.domain.viewHistory.service;

import com.mysettlement.domain.user.repository.UserRepository;
import com.mysettlement.domain.video.entity.Video;
import com.mysettlement.domain.video.exception.NoVideoFoundException;
import com.mysettlement.domain.video.repository.VideoRepository;
import com.mysettlement.domain.viewHistory.dto.request.ViewVideoRequestDto;
import com.mysettlement.domain.viewHistory.repository.ViewHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Sql(value = {"classpath:sql/userData.sql", "classpath:sql/videoData.sql", "classpath:sql/viewHistoryData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
@ExtendWith(OutputCaptureExtension.class) // 로그 출력 확인
class ViewHistoryServiceTest {

    @Autowired
    private ViewHistoryService viewHistoryService;
    @Autowired
    private ViewHistoryRepository viewHistoryRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("시청 기록이 없는 영상을 시청할 경우 조회수가 증가한다.")
    void testViewVideoThatHasNotViewHistory(CapturedOutput capturedOutput) {
        // given
        Long USER = 2L;
        Video savedVideo = saveAndGetNewVideo(USER);
        long videoView = videoRepository.findById(savedVideo.getId()).get().getVideoView();

        LocalDateTime NOW = LocalDateTime.now();
        ViewVideoRequestDto viewVideoRequestDto = new ViewVideoRequestDto(USER, NOW);
        // when
        viewHistoryService.viewVideo(savedVideo.getId(), viewVideoRequestDto);
        Video foundVideo = videoRepository.findById(savedVideo.getId()).get();
        // then
        assertThat(capturedOutput.toString()).contains("ViewHistory has been updated!");
        assertThat(foundVideo.getVideoView()).isEqualTo(videoView + 1);
    }

    @Test
    @DisplayName("이미 시청한 영상을 다시 시청할 경우 조회수가 증가하지 않는다.")
    void testViewVideoThatHasViewHistory() {
        // given
        Long USER = 2L;
        Long VIDEO = 1L;
        LocalDateTime NOW = LocalDateTime.now();
        long videoView = videoRepository.findById(VIDEO).get().getVideoView();
        ViewVideoRequestDto viewVideoRequestDto = new ViewVideoRequestDto(USER, NOW);
        // when
        viewHistoryService.viewVideo(VIDEO, viewVideoRequestDto);
        Video foundVideo = videoRepository.findById(VIDEO).get();
        // then
        assertThat(foundVideo.getVideoView()).isEqualTo(videoView);
    }

    @Test
    @DisplayName("존재하지 않는 비디오를 시청할 수 없다.")
    void testViewNoExistVideo() {
        // given
        Long USER = 2L;
        Long VIDEO = -1L;
        LocalDateTime NOW = LocalDateTime.now();
        ViewVideoRequestDto viewVideoRequestDto = new ViewVideoRequestDto(USER, NOW);
        // when
        // then
        assertThatThrownBy(() -> viewHistoryService.viewVideo(VIDEO, viewVideoRequestDto))
                .isInstanceOf(NoVideoFoundException.class)
                .hasMessageContaining("해당 비디오를 찾을 수 없습니다.");
    }

    private Video saveAndGetNewVideo(Long USER) {
        Video newVideo = Video.builder()
                .videoTitle("newVideoForViewTest")
                .videoDesc("new Video for a test")
                .user(userRepository.findById(USER).get())
                .videoLength(3600)
                .build();
        Video savedVideo = videoRepository.save(newVideo);
        return savedVideo;
    }
}