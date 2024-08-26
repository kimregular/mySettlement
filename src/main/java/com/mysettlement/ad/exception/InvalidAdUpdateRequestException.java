package com.mysettlement.ad.exception;

import com.mysettlement.globalException.MySettlementException;
import org.springframework.http.HttpStatus;

import static com.mysettlement.ad.exception.AdExceptionConstants.INVALID_AD_UPDATE_REQUEST_EXCEPTION;

public class InvalidAdUpdateRequestException extends MySettlementException {

    private static final String MESSAGE = INVALID_AD_UPDATE_REQUEST_EXCEPTION.getMessage();

    public InvalidAdUpdateRequestException() {
        super(MESSAGE);
    }

    @Override
    public HttpStatus getStatusCode() {
        return INVALID_AD_UPDATE_REQUEST_EXCEPTION.getStatus();
    }
}
