package com.librarySpring.librarySpring.Exceptions;

import com.librarySpring.librarySpring.Interfaces.ErrorMessage;

public class CannotDeleteResource extends RuntimeException{
    public CannotDeleteResource(ErrorMessage error) {
        super(error.getMessage());
    }
}
