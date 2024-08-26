package com.mysettlement.video.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideoUpdateRequestDto(@NotBlank(message = "제목은 필수값입니다.") String title, String desc,
                                    @NotNull(message = "이메일은 필수값입니다.") @Email(message = "이메일 형식이 올바르지 않습니다.") String email) {

    public VideoUpdateRequestDto {
        if (desc == null) {
            desc = "";
        }
    }
}
