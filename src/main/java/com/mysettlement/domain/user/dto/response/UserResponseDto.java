package com.mysettlement.domain.user.dto.response;

import com.mysettlement.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class UserResponseDto {

    private final String name;
    private final String email;

    @Builder(access = PRIVATE)
    private UserResponseDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static UserResponseDto of(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
