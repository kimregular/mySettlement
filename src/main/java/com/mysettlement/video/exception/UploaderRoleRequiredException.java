package com.mysettlement.video.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.video.exception.VideoExceptionConstants.*;

public class UploaderRoleRequiredException extends MySettlementException {

    private static final String MESSAGE = UPLOADER_ROLE_REQUIRED_EXCEPTION.getMESSAGE();

    public UploaderRoleRequiredException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return UPLOADER_ROLE_REQUIRED_EXCEPTION.getStatus();
    }
}
