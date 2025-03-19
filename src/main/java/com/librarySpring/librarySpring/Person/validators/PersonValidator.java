package com.librarySpring.librarySpring.Person.validators;

import com.librarySpring.librarySpring.Person.model.Person;

public class PersonValidator {
    public static void execute(Person person) {
        if (person.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (person.getPassword().isBlank() || person.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password does not meet the requirements");
        }
        if (person.getRole().isBlank()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
    }
}
