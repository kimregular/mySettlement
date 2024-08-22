package com.mysettlement.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSigninRequestDto {

    @NotBlank(message = "이름은 필수값입니다.")
    private final String name;

    @Email(message = "이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일은 필수값입니다.")
    private final String email;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private final String password;
}
