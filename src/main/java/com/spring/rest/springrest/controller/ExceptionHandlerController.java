package com.spring.rest.springrest.controller;

import com.spring.rest.springrest.enums.ErrorCodeEnum;
import com.spring.rest.springrest.exception.CustomException;
import com.spring.rest.springrest.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleCustomException(CustomException e) {

        return ErrorResponse.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInputParam(MethodArgumentTypeMismatchException e) {
        String paramName = e.getParameter().getParameter().getName();
        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(paramName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException e) {
        String fieldName = e.getBindingResult().getFieldError().getField();

        return ErrorResponse.builder()
                .code(ErrorCodeEnum.VALIDATION_ERROR.getCode())
                .message(fieldName + ErrorCodeEnum.VALIDATION_ERROR.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnknown(Exception e) {

        return ErrorResponse.builder()
                .code(ErrorCodeEnum.UNKNOWN_ERROR.getCode())
                .message(ErrorCodeEnum.UNKNOWN_ERROR.getMessage())
                .build();
    }
}
