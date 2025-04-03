package com.librarySpring.librarySpring.Entities.Person;

import com.librarySpring.librarySpring.Entities.Person.model.Person;
import com.librarySpring.librarySpring.Entities.Person.model.PersonDTO;
import com.librarySpring.librarySpring.Entities.Person.model.UpdatePersonCommand;
import com.librarySpring.librarySpring.Entities.Person.services.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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

    @MutationMapping
    public ResponseEntity<PersonDTO> createPerson(@Argument Person person) {
        return createPersonService.execute(person);
    }

    @QueryMapping
    public ResponseEntity<List<PersonDTO>> getPeople() {
        return getPeopleService.execute(null);
    }

    @QueryMapping
    public ResponseEntity<PersonDTO> getPersonById(@Argument String username) {
        return getPersonService.execute(username);
    }

    @QueryMapping
    public ResponseEntity<List<PersonDTO>> searchPersonByUsername(@Argument String username) {
        return searchPersonService.execute(username);
    }

    @MutationMapping
    public ResponseEntity<PersonDTO> updatePerson(@Argument String username, @Argument Person person) {
        return updatePersonService.execute(new UpdatePersonCommand(username, person));
    }

    @MutationMapping
    public ResponseEntity<Void> deletePerson(@Argument String username) {
        return deletePersonService.execute(username);
    }
}
