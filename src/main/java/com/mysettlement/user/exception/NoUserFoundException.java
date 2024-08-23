package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.user.exception.UserExceptionConstants.*;

public class NoUserFoundException extends MySettlementException {

    private static final String MESSAGE = NO_USER_FOUND_EXCEPTION.getMessage();

    public NoUserFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return NO_USER_FOUND_EXCEPTION.getStatus();
    }
}
