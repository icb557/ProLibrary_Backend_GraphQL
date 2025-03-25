package com.librarySpring.librarySpring.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handlerResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        String path = request.getRequestURI();
        return new ErrorResponse(exception.getMessage(), String.valueOf(HttpStatus.NOT_FOUND.value()), path);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlerResourceAlreadyExistsException(ResourceAlreadyExistsException exception, HttpServletRequest request) {
        String path = request.getRequestURI();
        return new ErrorResponse(exception.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), path);
    }

    @ExceptionHandler(AttributeNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlerAttributeNotValidException(AttributeNotValidException exception, HttpServletRequest request) {
        String path = request.getRequestURI();
        return new ErrorResponse(exception.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()), path);
    }
}
