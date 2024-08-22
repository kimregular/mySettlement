package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

public class DuplicateUserException extends MySettlementException {

    private static final String MESSAGE = "이미 존재하는 회원입니다.";

    public DuplicateUserException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }
}
