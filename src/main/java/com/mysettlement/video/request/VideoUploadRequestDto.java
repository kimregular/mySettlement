package com.mysettlement.video.request;

import com.mysettlement.user.entity.User;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VideoUploadRequestDto {

    @NotBlank(message = "제목은 필수 값입니다.")
    private final String title;

    @NotNull(message = "업로더는 필수 값입니다.")
    private final User user;

    @Nullable
    private final String desc;

}
