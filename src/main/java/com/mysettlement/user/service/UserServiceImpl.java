package com.mysettlement.user.service;

import com.mysettlement.user.entity.User;
import com.mysettlement.user.entity.UserRole;
import com.mysettlement.user.exception.DuplicateUserException;
import com.mysettlement.user.exception.NoUserFoundException;
import com.mysettlement.user.repository.UserRepository;
import com.mysettlement.user.request.UserSigninRequestDto;
import com.mysettlement.user.response.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserResponseDto findByUserName(String name) {
        User user = userRepository.findByName(name).orElseThrow(NoUserFoundException::new);
        return UserResponseDto.of(user);
    }

    @Override
    @Transactional
    public UserResponseDto signinUser(UserSigninRequestDto userSigninRequestDto) {
        if (isExistUser(userSigninRequestDto)) {
            throw new DuplicateUserException();
        }

        User newUser = User.builder()
                .name(userSigninRequestDto.getName())
                .email(userSigninRequestDto.getEmail())
                .password(userSigninRequestDto.getPassword())
                .userRole(UserRole.GUEST)
                .build();
        userRepository.save(newUser);
        return UserResponseDto.of(newUser);
    }

    private boolean isExistUser(UserSigninRequestDto userSigninRequestDto) {
        return userRepository.findByEmail(userSigninRequestDto.getEmail()).isPresent();
    }

}
