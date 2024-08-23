package com.mysettlement.video.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.mysettlement.video.exception.VideoExceptionConstants.*;

public class InvalidVideoUpdateRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_VIDEO_UPDATE_REQUEST_EXCEPTION.getMessage();

    public InvalidVideoUpdateRequestException() {
        super(MESSAGE);
    }

    public InvalidVideoUpdateRequestException(BindingResult errors) {
        super(MESSAGE);
        for (FieldError fieldError : errors.getFieldErrors()) {
            addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @Override
    public HttpStatus getStatusCode() {
        return INVALID_VIDEO_UPDATE_REQUEST_EXCEPTION.getStatus();
    }
}
