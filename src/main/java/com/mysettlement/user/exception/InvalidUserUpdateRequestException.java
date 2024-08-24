package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import static com.mysettlement.user.exception.UserExceptionConstants.*;

public class InvalidUserUpdateRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_USER_UPDATE_REQUEST_EXCEPTION.getMessage();

    public InvalidUserUpdateRequestException(BindingResult errors) {
        super(MESSAGE);
        for (FieldError fieldError : errors.getFieldErrors()) {
            addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @Override
    public HttpStatus getStatusCode() {
        return INVALID_USER_UPDATE_REQUEST_EXCEPTION.getStatus();
    }
}
