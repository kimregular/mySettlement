package com.mysettlement.globalException;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MySettlementGlobalExceptionHandler {

    @ExceptionHandler(MySettlementException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleMySettlementException(MySettlementException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e), e.getStatusCode());
    }
}
