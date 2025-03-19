package com.librarySpring.librarySpring.Person.services;

import com.librarySpring.librarySpring.Person.interfaces.Command;
import com.librarySpring.librarySpring.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Person.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeletePersonService implements Command<Integer, Void> {
    private final PersonRepository personRepository;

    public DeletePersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer input) {
        Optional<Person> personOptional = personRepository.findById(input);
        if (personOptional.isPresent()) {
            personRepository.deleteById(input);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
        // throw exception
    }
}
