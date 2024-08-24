package com.mysettlement.user.service;

import com.mysettlement.user.entity.UserRole;
import com.mysettlement.user.request.UserSigninRequestDto;
import com.mysettlement.user.response.UserResponseDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserService {

    UserResponseDto findByUserName(String name);

    @Transactional
    UserResponseDto signinUser(UserSigninRequestDto userSigninRequestDto);

    @Transactional
    UserResponseDto changeUserStatus(Long userId, UserRole userRole);
}
