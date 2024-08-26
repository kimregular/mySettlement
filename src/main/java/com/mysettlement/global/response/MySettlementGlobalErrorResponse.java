package com.mysettlement.global.response;

import com.mysettlement.global.exception.MySettlementException;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class MySettlementGlobalErrorResponse {

    private final HttpStatus status;
    private final String message;
    private final Map<String, String> validation;

    @Builder(access = PRIVATE)
    public MySettlementGlobalErrorResponse(HttpStatus status, String message, Map<String, String> validation) {
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

    public static MySettlementGlobalErrorResponse of(MethodArgumentNotValidException e) {
        Map<String, String> errorField = new HashMap<>();
        for (ObjectError allError : e.getAllErrors()) {
            String fieldName = ((FieldError) allError).getField();
            String errorMessage = allError.getDefaultMessage();
            errorField.put(fieldName, errorMessage);
        }

        return MySettlementGlobalErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message("유효하지 않은 요청입니다.")
                .validation(errorField)
                .build();
    }

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }
}
