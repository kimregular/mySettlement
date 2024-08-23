package com.mysettlement.ad.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AdExceptionConstants {

    INVALID_AD_UPLOAD_REQUEST_EXCEPTION("유효하지 않은 업로드 요청입니다.", HttpStatus.BAD_REQUEST),
    ;

    private final String message;
    private final HttpStatus status;
}
