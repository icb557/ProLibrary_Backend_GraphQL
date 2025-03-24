package com.librarySpring.librarySpring.Entities.Person;

import com.librarySpring.librarySpring.Interfaces.ErrorMessage;

public enum PersonErrorMessages implements ErrorMessage {
    PERSON_NOT_FOUND("Person not found"),
    PERSON_ALREADY_EXISTS("Person already exists"),
    NAME_REQUIRED("Name is required"),
    PASSWORD_NOT_VALID("Password does not meet the requirements"),
    ROLE_NOT_VALID("Role is not valid");

    private final String message;

    PersonErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
