package com.mysettlement.video.request;

import com.mysettlement.video.entity.VideoStatus;
import jakarta.validation.constraints.NotNull;

public record VideoStatusChangeRequestDto(@NotNull(message = "비디오 상태는 필수값입니다.") VideoStatus videoStatus) {
}
