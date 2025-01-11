package com.example.testplugin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This is a generated NotFoundException for demonstration purposes.
 */
@RestControllerAdvice
public class TestUserNotFoundAdvice {
    @ExceptionHandler(TestUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String TestUserNotFoundHandler(TestUserNotFoundException ex) {
        return ex.getMessage();
    }
}