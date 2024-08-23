package com.mysettlement.video.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideoUploadRequestDto(
        @NotBlank(message = "제목은 필수 값입니다.") String title,
        @Nullable String desc,
        @NotNull(message = "업로더는 필수 값입니다.") @Email(message = "이메일 형식이 아닙니다.") String userEmail
) {}
