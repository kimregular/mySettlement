package com.mysettlement.video.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysettlement.user.entity.User;
import com.mysettlement.user.entity.UserRole;
import com.mysettlement.video.exception.InvalidVideoUploadRequestException;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.response.VideoResponseDto;
import com.mysettlement.video.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.mysettlement.video.exception.VideoExceptionConstants.INVALID_VIDEO_UPLOAD_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideoController.class)
class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VideoService videoService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private User uploader;

    @BeforeEach
    void init() {
        uploader = User.builder()
                .name("uploader")
                .email("uploader@test.com")
                .userRole(UserRole.UPLOADER)
                .build();
    }

    @Test
    @DisplayName("비디오 업로드 테스트 - 정상")
    void test1() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("testVideo", uploader, "test video");
        Mockito.when(videoService.uploadVideo(Mockito.any(VideoUploadRequestDto.class))).thenReturn(new VideoResponseDto(videoUploadRequestDto.getTitle(), videoUploadRequestDto.getDesc()));
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/video")
                .content(objectMapper.writeValueAsString(videoUploadRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8");
        // then
        ResultActions resultActions = mockMvc.perform(request);
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(videoUploadRequestDto.getTitle()))
                .andExpect(jsonPath("$.desc").value(videoUploadRequestDto.getDesc()));
    }

    @Test
    @DisplayName("비디오 업로드 테스트 - 제목 없음")
    void test2() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto(null, uploader, "test video");
        Mockito.when(videoService.uploadVideo(Mockito.any(VideoUploadRequestDto.class))).thenReturn(new VideoResponseDto(videoUploadRequestDto.getTitle(), videoUploadRequestDto.getDesc()));
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/video")
                .content(objectMapper.writeValueAsString(videoUploadRequestDto))
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8");
        // then
        ResultActions resultActions = mockMvc.perform(request);
        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidVideoUploadRequestException.class)
                        .hasMessageContaining(INVALID_VIDEO_UPLOAD_EXCEPTION.getMESSAGE()))
                .andExpect(jsonPath("$.validation.title").value("제목은 필수 값입니다."));
    }
    
    @Test
    @DisplayName("비디오 업로드 테스트 - 업로더 없음")
    void test3() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("newVideo", null, "test video");
        Mockito.when(videoService.uploadVideo(Mockito.any(VideoUploadRequestDto.class))).thenReturn(new VideoResponseDto(videoUploadRequestDto.getTitle(), videoUploadRequestDto.getDesc()));
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/video")
                .content(objectMapper.writeValueAsString(videoUploadRequestDto))
                .contentType(MediaType.APPLICATION_JSON);

        // then
        ResultActions resultActions = mockMvc.perform(request);
        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidVideoUploadRequestException.class)
                        .hasMessageContaining(INVALID_VIDEO_UPLOAD_EXCEPTION.getMESSAGE()))
                .andExpect(jsonPath("$.validation.user").value("업로더는 필수 값입니다."));
    }
}