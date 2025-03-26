package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Entities.Person.PersonErrorMessages;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Interfaces.Query;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetPersonService implements Query<String, PersonDTO> {

    private final PersonRepository personRepository;

    public GetPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public ResponseEntity<PersonDTO> execute(String input) {
        Optional<Person> personOptional = personRepository.findByUsername(input);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(new PersonDTO(personOptional.get()));
        }
        throw new ResourceNotFoundException(PersonErrorMessages.PERSON_NOT_FOUND);
    }
}
