package com.librarySpring.librarySpring.Entities.Author;

import com.librarySpring.librarySpring.Interfaces.ErrorMessage;

public enum AuthorErrorMessages implements ErrorMessage {
    AUTHOR_NOT_FOUND("Author not found"),
    AUTHOR_ALREADY_EXISTS("Author already exists"),
    ID_INVALID("Id does not match the required format"),
    FIRSTNAME_REQUIRED("First Name cannot be empty"),
    LASTNAME_REQUIRED("Last Name cannot be empty"),
    SECOND_LASTNAME_REQUIRED("Second last Name cannot be empty"),
    NATIONALITY_REQUIRED("Nationality cannot be empty");

    private final String message;

    AuthorErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
