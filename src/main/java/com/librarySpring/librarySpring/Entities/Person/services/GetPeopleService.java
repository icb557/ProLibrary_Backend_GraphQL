package com.librarySpring.librarySpring.Entities.Person.services;

import com.librarySpring.librarySpring.Entities.Person.interfaces.PersonRepository;
import com.librarySpring.librarySpring.Interfaces.Query;
import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPeopleService {

    private final PersonRepository personRepository;

    public GetPeopleService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> execute(Void input) {
        List<Person> people = personRepository.findAll();
        List<PersonDTO> peopleDTO = people.stream().map(PersonDTO::new).toList();
        return peopleDTO;
    }
}
