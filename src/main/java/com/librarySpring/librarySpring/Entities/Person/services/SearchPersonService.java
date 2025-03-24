package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Interfaces.Query;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchPersonService implements Query<String, PersonDTO> {
    private final PersonRepository personRepository;

    public SearchPersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<PersonDTO> execute(String input) {
        Person person = personRepository.findByUsername(input);
        return ResponseEntity.ok(new PersonDTO(person));
    }
}
