package com.converter.error.exception;

public class FeignServerException extends BusinessException{

    public FeignServerException() {
        super(ErrorCode.FEIGN_SERVER_ERROR);
    }
}
