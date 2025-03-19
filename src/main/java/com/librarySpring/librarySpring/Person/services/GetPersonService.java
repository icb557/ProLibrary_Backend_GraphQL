package com.librarySpring.librarySpring.Person.services;

import com.librarySpring.librarySpring.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Person.interfaces.Query;
import com.librarySpring.librarySpring.Person.model.Person;
import com.librarySpring.librarySpring.Person.model.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetPersonService implements Query<Integer, PersonDTO> {

    private final PersonRepository personRepository;

    public GetPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public ResponseEntity<PersonDTO> execute(Integer input) {
        Optional<Person> personOptional = personRepository.findById(input);
        if (personOptional.isPresent()) {
            return ResponseEntity.ok(new PersonDTO(personOptional.get()));
        }
        return null;
        // throw exception
    }
}
