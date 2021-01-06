package com.converter.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", "Method Not Allowed"),
    ENTITY_NOT_FOUND(400, "C003", "Entity Not Found"),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403, "C006", "Access is Denied"),

    FEIGN_CLIENT_ERROR(400,"F001", "FEIGN_CLIENT_ERROR"),
    FEIGN_SERVER_ERROR(500,"F002", "FEIGN_SERVER_ERROR"),
    ;

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    @Getter
    @RequiredArgsConstructor
    public enum Group {
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        private final int groupCode;

        public static Group classify(int statusCode) {
            int groupCode = statusCode / 100;
            return Group.CLIENT_ERROR.groupCode == groupCode ?
                    Group.CLIENT_ERROR : Group.SERVER_ERROR;
        }
    }
}