package com.librarySpring.librarySpring.Entities.Person.model;

public class UpdatePersonCommand {
    private final String username;
    private final Person person;

    public UpdatePersonCommand(String username, Person person) {
        this.username = username;
        this.person = person;
    }

    public String getUsername() {
        return username;
    }

    public Person getPerson() {
        return person;
    }
}
