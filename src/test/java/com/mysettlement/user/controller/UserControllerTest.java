package com.mysettlement.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysettlement.user.entity.User;
import com.mysettlement.user.exception.InvalidSigninRequestException;
import com.mysettlement.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("회원가입 테스트 - 일반")
    void test1() throws Exception {
        // given
        User newUser = User.builder()
                .name("newUser")
                .email("newUser@test.com")
                .password("1234")
                .build();

        String signinRequest = objectMapper.writeValueAsString(newUser);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signinRequest);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 테스트 - 이름 공백")
    void test2() throws Exception {
        // given
        User newUser = User.builder()
                .name("")
                .email("newUser@test.com")
                .password("1234")
                .build();

        String signinRequest = objectMapper.writeValueAsString(newUser);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signinRequest);
        // then
        ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidSigninRequestException.class)
                        .hasMessageContaining("잘못된 회원가입 요청입니다."))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("잘못된 회원가입 요청입니다."))
                .andExpect(jsonPath("$.validation.name").value("이름은 필수값입니다."));

    }

    @Test
    @DisplayName("회원가입 테스트 - 이메일 공백")
    void test3() throws Exception {
        // given
        User newUser = User.builder()
                .name("newUser")
                .email("")
                .password("1234")
                .build();

        String signinRequest = objectMapper.writeValueAsString(newUser);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signinRequest);
        // then
        ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidSigninRequestException.class)
                        .hasMessageContaining("잘못된 회원가입 요청입니다."))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("잘못된 회원가입 요청입니다."))
                .andExpect(jsonPath("$.validation.email").value("이메일은 필수값입니다."));
    }

    @Test
    @DisplayName("회원가입 테스트 - 이메일 형식")
    void test4() throws Exception {
        // given
        User newUser = User.builder()
                .name("newUser")
                .email("invalidEmailFormat")
                .password("1234")
                .build();

        String signinRequest = objectMapper.writeValueAsString(newUser);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(signinRequest);
        // then
        ResultActions resultActions = mockMvc.perform(request);

        resultActions.andExpect(status().isBadRequest())
                .andExpect(result -> assertThat(result.getResolvedException())
                        .isInstanceOf(InvalidSigninRequestException.class)
                        .hasMessageContaining("잘못된 회원가입 요청입니다."))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("잘못된 회원가입 요청입니다."))
                .andExpect(jsonPath("$.validation.email").value("이메일 형식이 아닙니다."));
    }
}