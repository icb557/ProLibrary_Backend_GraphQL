package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.PersonErrorMessages;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Interfaces.Command;
import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Entities.Person.validators.PersonValidator;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import com.librarySpring.librarySpring.Entities.Person.model.UpdatePersonCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePersonService implements Command<UpdatePersonCommand, PersonDTO> {

    private final PersonRepository personRepository;

    public UpdatePersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<PersonDTO> execute(UpdatePersonCommand input) {
        Optional<Person> personOptional = personRepository.findById(input.getId());
        if (personOptional.isPresent()) {
            Person person = input.getPerson();
            person.setId(input.getId());
            PersonValidator.execute(person);
            personRepository.save(person);
            return ResponseEntity.ok(new PersonDTO(person));
        }
        throw new ResourceNotFoundException(PersonErrorMessages.PERSON_NOT_FOUND);
    }
}
