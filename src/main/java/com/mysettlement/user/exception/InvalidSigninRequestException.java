package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.mysettlement.user.exception.UserExceptionConstants.*;

public class InvalidSigninRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_SIGNININ_REQUEST_EXCEPTION.getMessage();

    public InvalidSigninRequestException(BindingResult errors) {
        super(MESSAGE);
        for (FieldError fieldError : errors.getFieldErrors()) {
            addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @Override
    public HttpStatus getStatusCode() {
        return INVALID_SIGNININ_REQUEST_EXCEPTION.getStatus();
    }
}
