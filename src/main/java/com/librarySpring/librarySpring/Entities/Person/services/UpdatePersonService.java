package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.PersonErrorMessages;
import com.librarySpring.librarySpring.Exceptions.ResourceAlreadyExistsException;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Interfaces.Command;
import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Entities.Person.validators.PersonValidator;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import com.librarySpring.librarySpring.Entities.Person.model.UpdatePersonCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePersonService implements Command<UpdatePersonCommand, PersonDTO> {

    private final PasswordEncoder passwordEncoder;

    private final PersonRepository personRepository;

    public UpdatePersonService(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PersonDTO execute(UpdatePersonCommand input) {
        Optional<Person> personOptional = personRepository.findByUsername(input.getUsername());
        if (personOptional.isPresent()) {
            Person person = input.getPerson();
            PersonDTO personDTO = new PersonDTO(person);
            PersonValidator.execute(personDTO);
            if (personRepository.findByUsername(person.getUsername()).isPresent()) {
                throw new ResourceAlreadyExistsException(PersonErrorMessages.PERSON_ALREADY_EXISTS);
            }
            person.setId(personOptional.get().getId());
            personRepository.updatePerson(
                    person.getId(),
                    person.getUsername(),
                    passwordEncoder.encode(person.getPassword()),
                    person.getRole()
            );
            return personDTO;
        }
        throw new ResourceNotFoundException(PersonErrorMessages.PERSON_NOT_FOUND);
    }
}
