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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreatePersonService implements Command<Person, PersonDTO> {

    private final PersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;

    public CreatePersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PersonDTO execute(Person person) {
        Optional<Person> personOptional = personRepository.findByUsername(person.getUsername());
        if (personOptional.isPresent()) {
            throw new ResourceAlreadyExistsException(PersonErrorMessages.PERSON_ALREADY_EXISTS);
        }
        PersonDTO personDto = new PersonDTO(person);
        PersonValidator.execute(personDto);
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        personRepository.save(person);
        return personDto;
    }
}
