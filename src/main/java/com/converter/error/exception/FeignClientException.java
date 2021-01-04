package com.converter.error.exception;

public class FeignClientException extends BusinessException{

    public FeignClientException() {
        super(ErrorCode.FEIGN_CLIENT_ERROR);
    }
}
