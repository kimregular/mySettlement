package com.mysettlement.video.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum VideoExceptionConstants {

    INVALID_VIDEO_UPLOAD_EXCEPTION("유효하지 않은 업로드 요청입니다.", HttpStatus.BAD_REQUEST);

    private final String MESSAGE;
    private final HttpStatus status;
}
