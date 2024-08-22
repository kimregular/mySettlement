package com.mysettlement.user.repository;

import com.mysettlement.user.entity.User;
import com.mysettlement.user.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void init() {
        User initUser = User.builder()
                .name("initUser")
                .email("initUser@test.com")
                .password("1234")
                .userRole(UserRole.DEFAULT)
                .build();
        userRepository.save(initUser);
    }

    @Test
    @DisplayName("이름으로 유저 조회 성공")
    void test1() {
        // given
        User testUser = User.builder()
                .name("testUser")
                .email("testUser@test.com")
                .password("4321")
                .userRole(UserRole.DEFAULT)
                .build();
        userRepository.save(testUser);
        // when

        Optional<User> foundUser = userRepository.findByName(testUser.getName());
        // then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo(testUser.getName());
    }

    @Test
    @DisplayName("없는 유저 이름 조회 실패")
    void test2() {
        // given
        // when
        Optional<User> noExist = userRepository.findByName("noExist");
        // then
        assertThat(noExist).isEmpty();
    }

    @Test
    @DisplayName("모든 유저 조회 테스트")
    void test3() {
        // given
        User testUser = User.builder()
                .name("testUser")
                .email("testUser@test.com")
                .password("4321")
                .userRole(UserRole.DEFAULT)
                .build();
        userRepository.save(testUser);
        // when
        List<User> users = userRepository.findAll();
        // then
        assertThat(users).hasSize(2);
    }

    @Test
    @DisplayName("유저 이메일로 조회 테스트")
    void test4(){
        // given
        User testUser = User.builder()
                .name("testUser")
                .email("testUser@test.com")
                .password("4321")
                .userRole(UserRole.DEFAULT)
                .build();
        userRepository.save(testUser);
        // when
        Optional<User> foundUser = userRepository.findByEmail(testUser.getEmail());
        // then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo(testUser.getEmail());
    }

}