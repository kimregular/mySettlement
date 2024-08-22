package com.mysettlement.globalResponse;

import com.mysettlement.globalException.MySettlementException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class MySettlementGlobalErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final Map<String, String> validation;

    @Builder(access = PRIVATE)
    private MySettlementGlobalErrorResponse(HttpStatus status, String message, Map<String, String> validation) {
        this.status = status;
        this.message = message;
        this.validation = validation != null ? validation : new HashMap<>();
    }

    public static MySettlementGlobalErrorResponse of(MySettlementException e) {
        return MySettlementGlobalErrorResponse.builder()
                .status(e.getStatusCode())
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
