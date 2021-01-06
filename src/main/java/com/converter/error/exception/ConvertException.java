package com.converter.error.exception;

public class ConvertException extends BusinessException {

    public ConvertException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }

    public ConvertException(String message, Exception e, ErrorCode errorCode) {
        super(message, e, errorCode);
    }

    public ConvertException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
