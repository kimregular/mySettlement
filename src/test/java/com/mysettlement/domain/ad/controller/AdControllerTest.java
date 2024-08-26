package com.mysettlement.domain.ad.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysettlement.domain.ad.controller.AdController;
import com.mysettlement.domain.ad.request.AdUploadRequestDto;
import com.mysettlement.domain.ad.service.AdService;
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

@WebMvcTest(AdController.class)
class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdService adService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("광고 업로드 테스트")
    void testUploadAd() throws Exception {
        // given
        AdUploadRequestDto adUploadRequestDto = new AdUploadRequestDto("new Ad", "new Ad desc");
        String json = objectMapper.writeValueAsString(adUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/ad/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("200"));
    }

    @Test
    @DisplayName("광고 업로스 요청시 제목은 필수값이다.")
    void testUploadAdWithoutTitle() throws Exception {
        // given
        AdUploadRequestDto adUploadRequestDto = new AdUploadRequestDto("", "new Ad desc");
        String json = objectMapper.writeValueAsString(adUploadRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/ad/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.validation.title").value("제목은 필수값입니다."));
    }
}