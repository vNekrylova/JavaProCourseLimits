package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.ErrorDto;
import org.example.exceptions.LimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Что-то пошло не так");
    }

    @ExceptionHandler(LimitException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(LimitException e) {
        log.error(e.getMessage(), e);
        return new ErrorDto(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}
