package com.mysettlement.domain.viewHistory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysettlement.domain.viewHistory.dto.request.ViewVideoRequestDto;
import com.mysettlement.domain.viewHistory.service.ViewHistoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ViewHistoryController.class)
class ViewHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ViewHistoryService viewHistoryService;

    @Test
    @DisplayName("비디오 시청 테스트")
    void testViewVideo() throws Exception {
        // given
        LocalDateTime NOW = LocalDateTime.now();
        ViewVideoRequestDto viewVideoRequestDto = new ViewVideoRequestDto(2L, NOW);
        String json = objectMapper.writeValueAsString(viewVideoRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/view/{videoId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());
    }
}