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

    @GetMapping("/person/{username}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable String username) {
        return getPersonService.execute(username);
    }

    @GetMapping("/person/search")
    public ResponseEntity<List<PersonDTO>> searchPersonByUsername(@RequestParam String username) {
        return searchPersonService.execute(username);
    }

    @PutMapping("/person/{username}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable String username, @RequestBody Person person) {
        return updatePersonService.execute(new UpdatePersonCommand(username, person));
    }

    @DeleteMapping("/person/{username}")
    public ResponseEntity<Void> deletePerson(@PathVariable String username) {
        return deletePersonService.execute(username);
    }
}
