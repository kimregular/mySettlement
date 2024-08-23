package com.mysettlement.video.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.video.exception.VideoExceptionConstants.*;

public class NoVideoFoundException extends MySettlementException {

    private static final String MESSAGE = NO_VIDEO_FOUND_EXCEPTION.getMessage();

    public NoVideoFoundException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return NO_VIDEO_FOUND_EXCEPTION.getStatus();
    }
}
