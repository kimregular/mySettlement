package com.mysettlement.video.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoUploadRequestDto {

    @NotBlank(message = "제목은 필수 값입니다.")
    private final String title;

    @Nullable
    private final String desc;

    @NotNull(message = "업로더는 필수 값입니다.")
    @Email(message = "이메일 형식이 아닙니다.")
    private final String userEmail;


}
