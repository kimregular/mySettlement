package com.mysettlement.video.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record VideoUploadRequestDto(@NotBlank(message = "제목은 필수값입니다.") String title,
                                    @NotNull(message = "비디오 길이는 필수값입니다.") @Positive(message = "비디오 길이는 양수값이여야합니다.") Long videoLength,
                                    @Nullable String desc) {
    public VideoUploadRequestDto {
        if (desc == null) {
            desc = "";
        }
    }
}
