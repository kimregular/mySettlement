package com.mysettlement.user.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class InvalidSigninRequestException extends MySettlementException {

    private static final String MESSAGE = "잘못된 회원가입 요청입니다.";

    public InvalidSigninRequestException(BindingResult errors) {
        super(MESSAGE);
        for (FieldError fieldError : errors.getFieldErrors()) {
            addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
