package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.user.exception.UserExceptionConstants.*;

public class DuplicateUserException extends MySettlementException {

    private static final String MESSAGE = DUPLICATE_USER_EXCEPTION.getMessage();

    public DuplicateUserException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return DUPLICATE_USER_EXCEPTION.getStatus();
    }
}
