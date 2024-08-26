package com.mysettlement.video.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.video.exception.VideoExceptionConstants.INVALID_VIDEO_UPDATE_REQUEST_EXCEPTION;

public class InvalidVideoUpdateRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_VIDEO_UPDATE_REQUEST_EXCEPTION.getMessage();

    public InvalidVideoUpdateRequestException() {
        super(MESSAGE);
    }


    @Override
    public HttpStatus getStatusCode() {
        return INVALID_VIDEO_UPDATE_REQUEST_EXCEPTION.getStatus();
    }
}
