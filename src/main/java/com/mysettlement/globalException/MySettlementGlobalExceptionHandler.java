package com.mysettlement.globalException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MySettlementGlobalExceptionHandler {

    @ExceptionHandler(MySettlementException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleMySettlementException(MySettlementException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e), e.getStatusCode());
    }

    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleJsonMappingException(JsonMappingException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e), HttpStatus.BAD_REQUEST);
    }
}
