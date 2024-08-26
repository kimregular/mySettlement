package com.mysettlement.domain.user.service;

import com.mysettlement.domain.user.entity.UserRole;
import com.mysettlement.domain.user.request.UserSigninRequestDto;
import com.mysettlement.domain.user.response.UserResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {

    UserResponseDto findByUserName(String name);

    @Transactional
    UserResponseDto signinUser(UserSigninRequestDto userSigninRequestDto);

    @Transactional
    UserResponseDto changeUserStatus(Long userId, UserRole userRole);
}
