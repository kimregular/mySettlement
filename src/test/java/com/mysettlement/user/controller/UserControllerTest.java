package com.mysettlement.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysettlement.user.request.UserSigninRequestDto;
import com.mysettlement.user.service.UserService;
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

import static com.mysettlement.user.exception.UserExceptionConstants.INVALID_SIGNININ_REQUEST_EXCEPTION;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 회원가입 테스트")
    void testSiginin() throws Exception {
        // given
        UserSigninRequestDto userSigninRequestDto = new UserSigninRequestDto("newTestUser", "newSiginin@test.com", "1234");
        String json = objectMapper.writeValueAsString(userSigninRequestDto);


        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        // then
        mockMvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("회원가입 요청시 이름은 필수값이다.")
    void testSigninWithoutName() throws Exception {
        // given
        UserSigninRequestDto userSigninRequestDto = new UserSigninRequestDto("", "newSignin@test.com", "1234");
        String json = objectMapper.writeValueAsString(userSigninRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value(INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage()))
                .andExpect(jsonPath("$.validation.name").value("이름은 필수값입니다."));
    }

    @Test
    @DisplayName("회원가입 요청시 이메일은 필수값이다.")
    void testSigninWithoutEmail() throws Exception {
        // given
        UserSigninRequestDto userSigninRequestDto = new UserSigninRequestDto("newTestUser", "", "1234");
        String json = objectMapper.writeValueAsString(userSigninRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value(INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage()))
                .andExpect(jsonPath("$.validation.email").value("이메일은 필수값입니다."));
    }

    @Test
    @DisplayName("회원가입 요청시 이메일은 이메일 형식이여야한다.")
    void testSigninInvalidEmail() throws Exception {
        // given
        UserSigninRequestDto userSigninRequestDto = new UserSigninRequestDto("newTestUser", "newSignintest.com", "1234");
        String json = objectMapper.writeValueAsString(userSigninRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value(INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage()))
                .andExpect(jsonPath("$.validation.email").value("이메일 형식이 아닙니다."));
    }

    @Test
    @DisplayName("회원가입 요청시 비밀번호는 필수값이다.")
    void testSigninWithoutPassword() throws Exception {
        // given
        UserSigninRequestDto userSigninRequestDto = new UserSigninRequestDto("newTestUser", "newSignin@test.com", "");
        String json = objectMapper.writeValueAsString(userSigninRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value(INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage()))
                .andExpect(jsonPath("$.validation.password").value("비밀번호는 필수값입니다."));
    }

    @Test
    @DisplayName("이름과 이메일, 비밀번호가 없으면 회원가입이 불가능하다.")
    void testSigninWithoutNameAndEmailAndPassword() throws Exception {
        // given
        UserSigninRequestDto userSigninRequestDto = new UserSigninRequestDto("", "newSignintest.com", "");
        String json = objectMapper.writeValueAsString(userSigninRequestDto);
        // when
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);
        // then
        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value(INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage()))
                .andExpect(jsonPath("$.validation.name").value("이름은 필수값입니다."))
                .andExpect(jsonPath("$.validation.email").value("이메일 형식이 아닙니다."))
                .andExpect(jsonPath("$.validation.password").value("비밀번호는 필수값입니다."));
    }

}