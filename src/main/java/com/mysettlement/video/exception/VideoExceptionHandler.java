package com.mysettlement.video.exception;

import com.mysettlement.globalResponse.MySettlementGlobalErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.mysettlement.video.exception.VideoExceptionConstants.INVALID_VIDEO_UPLOAD_EXCEPTION;
import static com.mysettlement.video.exception.VideoExceptionConstants.UPLOADER_ROLE_REQUIRED_EXCEPTION;

@RestControllerAdvice
public class VideoExceptionHandler {

    @ExceptionHandler(InvalidVideoUploadRequestException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleInvalidVideoUploadRequestException(InvalidVideoUploadRequestException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e),
                INVALID_VIDEO_UPLOAD_EXCEPTION.getStatus());
    }

    @ExceptionHandler(UploaderRoleRequiredException.class)
    public ResponseEntity<MySettlementGlobalErrorResponse> handleUploaderRoleRequiredException(UploaderRoleRequiredException e) {
        return new ResponseEntity<>(MySettlementGlobalErrorResponse.of(e),
                UPLOADER_ROLE_REQUIRED_EXCEPTION.getStatus());
    }
}
