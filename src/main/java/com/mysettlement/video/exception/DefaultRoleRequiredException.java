package com.mysettlement.video.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.video.exception.VideoExceptionConstants.*;

public class DefaultRoleRequiredException extends MySettlementException {

    private static final String MESSAGE = DEFAULT_ROLE_REQUIRED_EXCEPTION.getMessage();

    public DefaultRoleRequiredException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return DEFAULT_ROLE_REQUIRED_EXCEPTION.getStatus();
    }
}
