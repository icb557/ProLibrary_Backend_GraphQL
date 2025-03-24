package com.librarySpring.librarySpring.Entities.Person;

import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import com.librarySpring.librarySpring.Entities.Person.model.UpdatePersonCommand;
import com.librarySpring.librarySpring.Entities.Person.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private final CreatePersonService createPersonService;
    private final GetPeopleService getPeopleService;
    private final GetPersonService getPersonService;
    private final SearchPersonService searchPersonService;
    private final UpdatePersonService updatePersonService;
    private final DeletePersonService deletePersonService;

    public PersonController(CreatePersonService createPersonService, GetPeopleService getPeopleService, GetPersonService getPersonService, SearchPersonService searchPersonService, UpdatePersonService updatePersonService, DeletePersonService deletePersonService) {
        this.createPersonService = createPersonService;
        this.getPeopleService = getPeopleService;
        this.getPersonService = getPersonService;
        this.searchPersonService = searchPersonService;
        this.updatePersonService = updatePersonService;
        this.deletePersonService = deletePersonService;
    }

    @PostMapping("/person")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody Person person) {
        return createPersonService.execute(person);
    }

    @GetMapping("/people")
    public ResponseEntity<List<PersonDTO>> getPeople() {
        return getPeopleService.execute(null);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Integer id) {
        return getPersonService.execute(id);
    }

    @GetMapping("/person/search")
    public ResponseEntity<PersonDTO> searchPersonByUsername(@RequestParam String username) {
        return searchPersonService.execute(username);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        return updatePersonService.execute(new UpdatePersonCommand(id, person));
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        return deletePersonService.execute(id);
    }
}
