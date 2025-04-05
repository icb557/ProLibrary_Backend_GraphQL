package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.PersonErrorMessages;
import com.librarySpring.librarySpring.Interfaces.Command;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeletePersonService implements Command<String, Void> {
    private final PersonRepository personRepository;

    public DeletePersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Void execute(String input) {
        Optional<Person> personOptional = personRepository.findByUsername(input);
        personOptional.ifPresent(person -> personRepository.deleteById(person.getId()));
        throw new ResourceNotFoundException(PersonErrorMessages.PERSON_NOT_FOUND);
    }
}
