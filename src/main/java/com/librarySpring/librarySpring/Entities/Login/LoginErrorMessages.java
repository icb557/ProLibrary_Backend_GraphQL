package com.librarySpring.librarySpring.Entities.Login;

import com.librarySpring.librarySpring.Interfaces.ErrorMessage;

public enum LoginErrorMessages implements ErrorMessage {
    LOGIN_ERROR("Login error, check your credentials");

    private final String message;

    LoginErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
