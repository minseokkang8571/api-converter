package com.converter.error.exception;

public class ConvertException extends BusinessException {

    public ConvertException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
