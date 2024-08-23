package com.mysettlement.user.service;

import com.mysettlement.user.entity.User;
import com.mysettlement.user.entity.UserRole;
import com.mysettlement.user.exception.DuplicateUserException;
import com.mysettlement.user.exception.NoUserFoundException;
import com.mysettlement.user.repository.UserRepository;
import com.mysettlement.user.request.UserSigninRequestDto;
import com.mysettlement.user.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static com.mysettlement.user.exception.UserExceptionConstants.DUPLICATE_USER_EXCEPTION;
import static com.mysettlement.user.exception.UserExceptionConstants.NO_USER_FOUND_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(UserServiceImpl.class)
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    User initUser;


    @BeforeEach
    void init() {
        initUser = User.builder()
                .name("initUser")
                .email("initUser@test.com")
                .password("1234")
                .userRole(UserRole.DEFAULT)
                .build();
        userRepository.save(initUser);
    }


    @Test
    @DisplayName("유저 이름으로 조회")
    void test1(){
        // given
        User testUser = User.builder()
                .name("testUser")
                .email("testUser@test.com")
                .password("4321")
                .userRole(UserRole.DEFAULT)
                .build();
        userRepository.save(testUser);
        // when
        UserResponseDto foundUser = userService.findByUserName(testUser.getName());
        // then
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getName()).isEqualTo(testUser.getName());
    }

    @Test
    @DisplayName("없는 유저 이름으로 조회 실패")
    void test2(){
        // given
        // when
        // then
        assertThatThrownBy(() -> userService.findByUserName("NO"))
                .isInstanceOf(NoUserFoundException.class)
                .hasMessageContaining(NO_USER_FOUND_EXCEPTION.getMessage());
    }

    @Test
    @DisplayName("유저 회원가입 테스트 - 정상")
    void test3(){
        // given
        UserSigninRequestDto newUserRequest = new UserSigninRequestDto("newUser1", "email@test.com", "1234");
        // when
        UserResponseDto userResponseDto = userService.signinUser(newUserRequest);
        // then
        assertThat(userResponseDto.getName()).isEqualTo(newUserRequest.getName());
    }

    @Test
    @DisplayName("유저 회원가입 테스트 - 중복")
    void test4(){
        // given
        UserSigninRequestDto testUser = new UserSigninRequestDto(initUser.getName(), initUser.getEmail(), initUser.getPassword());
        // when
        // then
        assertThatThrownBy(() -> userService.signinUser(testUser))
                .isInstanceOf(DuplicateUserException.class)
                .hasMessageContaining(DUPLICATE_USER_EXCEPTION.getMessage());
    }

}

