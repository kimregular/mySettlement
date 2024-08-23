package com.mysettlement.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionConstants {

    NO_USER_FOUND_EXCEPTION("없는 사용자입니다.", HttpStatus.NOT_FOUND),
    INVALID_SIGNININ_REQUEST_EXCEPTION("잘못된 회원가입 요청입니다.", HttpStatus.BAD_REQUEST),
    DUPLICATE_USER_EXCEPTION("이미 존재하는 회원입니다.", HttpStatus.CONFLICT);

    private final String message;
    private final HttpStatus status;
    }
