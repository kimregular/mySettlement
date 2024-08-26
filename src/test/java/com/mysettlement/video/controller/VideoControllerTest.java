package com.mysettlement.video.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysettlement.video.request.VideoUploadRequestDto;
import com.mysettlement.video.service.VideoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VideoController.class)
class VideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VideoService videoService;

    @Test
    @DisplayName("비디오 업로드 테스트")
    void testUploadVideo() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("newVideo", 3600L, "new video desc");
        String json = objectMapper.writeValueAsString(videoUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/video/1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));
    }

    @Test
    @DisplayName("업로드 요청시 비디오 제목은 필수값이다.")
    void testUploadWithoutTitle() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("", 3600L, "new video desc");
        String json = objectMapper.writeValueAsString(videoUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/video/1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.validation.title").value("제목은 필수값입니다."));
    }

    @Test
    @DisplayName("업로드 요청시 비디오 길이는 필수값이다.")
    void testUploadWithoutVideoLength() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("newVideo", null, "new video desc");
        String json = objectMapper.writeValueAsString(videoUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/video/1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.validation.videoLength").value("비디오 길이는 필수값입니다."));
    }

    @Test
    @DisplayName("업로드 요청시 비디오 길이는 양수값이다.")
    void testUploadWithNegativeVideoLength() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("newVideo", -1L, "new video desc");
        String json = objectMapper.writeValueAsString(videoUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/video/1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.validation.videoLength").value("비디오 길이는 양수값이여야합니다."));
    }

    @Test
    @DisplayName("업로드 요청시 제목과 비디오 길이는 필수값이다.")
    void testUploadWithoutTitleAndVideoLength() throws Exception {
        // given
        VideoUploadRequestDto videoUploadRequestDto = new VideoUploadRequestDto("", null, "new video desc");
        String json = objectMapper.writeValueAsString(videoUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/video/1/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.validation.title").value("제목은 필수값입니다."))
                .andExpect(jsonPath("$.validation.videoLength").value("비디오 길이는 필수값입니다."));
    }
}