package com.mysettlement.video.exception;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VideoExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidVideoUploadRequestException.class)
    public MySettlementGlobalErrorResponse handleInvalidVideoUploadRequestException(InvalidVideoUploadRequestException e) {
        return MySettlementGlobalErrorResponse.of(e);
    }
}
