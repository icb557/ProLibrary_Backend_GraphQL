package com.librarySpring.librarySpring.Exceptions;

import com.librarySpring.librarySpring.Interfaces.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(ErrorMessage error) {
        super(error.getMessage());
    }
}
