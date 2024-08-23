package com.mysettlement.user.exception;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import com.mysettlement.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.mysettlement.user.exception.UserExceptionConstants.*;

@Slf4j
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleNoUserFoundException(NoUserFoundException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e),
                NO_USER_FOUND_EXCEPTION.getStatus());
    }

    @ExceptionHandler(InvalidSigninRequestException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleInvalidSigninRequestException(InvalidSigninRequestException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e),
                INVALID_SIGNININ_REQUEST_EXCEPTION.getStatus());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleDuplicateUserException(DuplicateUserException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e),
                DUPLICATE_USER_EXCEPTION.getStatus());
    }
}
