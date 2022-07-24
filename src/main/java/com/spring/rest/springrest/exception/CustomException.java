package com.spring.rest.springrest.exception;

import com.spring.rest.springrest.enums.ErrorCodeEnum;

public class CustomException extends RuntimeException{

    private final int code;
    private final String message;


    public CustomException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.message = errorCodeEnum.getMessage();
        this.code = errorCodeEnum.getCode();
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
