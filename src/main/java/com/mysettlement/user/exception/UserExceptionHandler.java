package com.mysettlement.user.exception;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import com.mysettlement.user.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseBody
@ControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoUserFoundException.class)
    public MySettlementGlobalErrorResponse noUserFoundException(NoUserFoundException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidSigninRequestException.class)
    public MySettlementGlobalErrorResponse invalidSigninRequestException(InvalidSigninRequestException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateUserException.class)
    public MySettlementGlobalErrorResponse duplicateUserException(DuplicateUserException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }
}
