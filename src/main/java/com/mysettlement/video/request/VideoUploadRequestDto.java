package com.mysettlement.video.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record VideoUploadRequestDto(
        @NotBlank(message = "제목은 필수 값입니다.") String title,
        @Nullable String desc
) {}
