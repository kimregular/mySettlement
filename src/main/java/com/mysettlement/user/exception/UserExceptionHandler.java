package com.mysettlement.user.exception;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import com.mysettlement.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoUserFoundException.class)
    public MySettlementGlobalErrorResponse handleNoUserFoundException(NoUserFoundException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidSigninRequestException.class)
    public MySettlementGlobalErrorResponse handleInvalidSigninRequestException(InvalidSigninRequestException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateUserException.class)
    public MySettlementGlobalErrorResponse handleDuplicateUserException(DuplicateUserException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }
}
