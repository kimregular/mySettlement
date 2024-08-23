package com.mysettlement.ad.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.mysettlement.ad.exception.AdExceptionConstants.*;

public class InvalidAdUploadRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_AD_UPLOAD_REQUEST_EXCEPTION.getMessage();

    public InvalidAdUploadRequestException(BindingResult errors) {
        super(MESSAGE);
        for (FieldError fieldError : errors.getFieldErrors()) {
            addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @Override
    public HttpStatus getStatusCode() {
        return INVALID_AD_UPLOAD_REQUEST_EXCEPTION.getStatus();
    }
}
