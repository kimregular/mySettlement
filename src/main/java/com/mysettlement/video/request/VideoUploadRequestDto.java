package com.mysettlement.video.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VideoUploadRequestDto(
        @NotBlank(message = "제목은 필수값입니다.") String title,
        @NotNull(message = "비디오 길이는 필수값입니다.") long videoLength,
         String desc
) {
    public VideoUploadRequestDto {
        if (desc == null) {
            desc = "";
        }
    }
}
