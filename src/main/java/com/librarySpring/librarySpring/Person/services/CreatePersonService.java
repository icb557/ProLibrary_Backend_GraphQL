package com.librarySpring.librarySpring.Person.services;

import com.librarySpring.librarySpring.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Person.interfaces.Command;
import com.librarySpring.librarySpring.Person.model.Person;
import com.librarySpring.librarySpring.Person.model.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonService implements Command<Person, PersonDTO> {

    private final PersonRepository personRepository;

    public CreatePersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public ResponseEntity<PersonDTO> execute(Person person) {
        // PersonValidator.execute(person);
        personRepository.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PersonDTO(person));
    }
}
