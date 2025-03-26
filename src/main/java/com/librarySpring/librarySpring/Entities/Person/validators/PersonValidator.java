package com.librarySpring.librarySpring.Entities.Person.validators;

import com.librarySpring.librarySpring.Entities.Person.Enums.PersonRoles;
import com.librarySpring.librarySpring.Entities.Person.PersonErrorMessages;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import com.librarySpring.librarySpring.Exceptions.AttributeNotValidException;

public class PersonValidator {
    public static void execute(PersonDTO person) {
        if (person.getUsername().isBlank()) {
            throw new AttributeNotValidException(PersonErrorMessages.NAME_REQUIRED);
        }
        if (person.getPassword().isBlank() || person.getPassword().length() < 8) {
            throw new AttributeNotValidException(PersonErrorMessages.PASSWORD_NOT_VALID);
        }
        if (person.getRole().isBlank() || !PersonRoles.isValidRole(person.getRole())) {
            throw new AttributeNotValidException(PersonErrorMessages.ROLE_NOT_VALID);
        }
    }
}
