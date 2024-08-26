package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.user.exception.UserExceptionConstants.INVALID_SIGNININ_REQUEST_EXCEPTION;

public class InvalidSigninRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage();

    public InvalidSigninRequestException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return INVALID_SIGNININ_REQUEST_EXCEPTION.getStatus();
    }
}
