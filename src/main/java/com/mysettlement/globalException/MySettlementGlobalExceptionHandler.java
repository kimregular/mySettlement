package com.mysettlement.globalException;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import com.mysettlement.user.exception.InvalidSigninRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MySettlementGlobalExceptionHandler {

    @ExceptionHandler(MySettlementException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleMySettlementException(MySettlementException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e), e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleJsonMappingException(MethodArgumentNotValidException e) {
        InvalidSigninRequestException invalidSigninRequestException = new InvalidSigninRequestException();
        for (ObjectError allError : e.getAllErrors()) {
            String fieldName = ((FieldError) allError).getField();
            String errorMessage = allError.getDefaultMessage();
            invalidSigninRequestException.addValidation(fieldName, errorMessage);
        }
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(invalidSigninRequestException), HttpStatus.BAD_REQUEST);
    }
}
