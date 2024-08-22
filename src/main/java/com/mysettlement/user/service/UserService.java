package com.mysettlement.user.service;

import com.mysettlement.user.request.UserSigninRequestDto;
import com.mysettlement.user.response.UserResponseDto;

public interface UserService {

    UserResponseDto findByUserName(String name);

    UserResponseDto signinUser(UserSigninRequestDto userSigninRequestDto);
}
