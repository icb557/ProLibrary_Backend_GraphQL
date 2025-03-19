package com.librarySpring.librarySpring.Person.model;

import lombok.Data;

public class UpdatePersonCommand {
    private final Integer id;
    private final Person person;

    public UpdatePersonCommand(Integer id, Person person) {
        this.id = id;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }
}
