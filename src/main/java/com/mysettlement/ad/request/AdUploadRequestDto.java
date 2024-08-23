package com.mysettlement.ad.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record AdUploadRequestDto(@NotBlank(message = "제목은 필수값입니다.") String title,
                                 @Nullable String desc) {
}
