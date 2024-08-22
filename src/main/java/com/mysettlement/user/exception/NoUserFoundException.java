package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

public class NoUserFoundException extends MySettlementException {

    private static final String MESSAGE = "없는 사용자입니다.";

    public NoUserFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.NOT_FOUND;
    }
}
