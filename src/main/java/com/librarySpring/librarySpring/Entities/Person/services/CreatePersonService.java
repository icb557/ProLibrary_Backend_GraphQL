package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Entities.Person.validators.PersonValidator;
import com.librarySpring.librarySpring.Entities.Person.PersonErrorMessages;
import com.librarySpring.librarySpring.Exceptions.ResourceAlreadyExistsException;
import com.librarySpring.librarySpring.Interfaces.Command;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreatePersonService implements Command<Person, PersonDTO> {

    private final PersonRepository personRepository;

    public CreatePersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<PersonDTO> execute(Person person) {
        Optional<Person> personOptional = personRepository.findById(person.getId());
        if (personOptional.isPresent()) {
            throw new ResourceAlreadyExistsException(PersonErrorMessages.PERSON_ALREADY_EXISTS);
        }
        PersonValidator.execute(person);
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonDTO(person));
    }
}
