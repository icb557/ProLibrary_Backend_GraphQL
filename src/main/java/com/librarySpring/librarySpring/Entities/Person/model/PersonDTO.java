package com.librarySpring.librarySpring.Entities.Person.model;

import lombok.Data;

@Data
public class PersonDTO {
    private int id;
    private String username;
    private String password;
    private String role;

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.role = person.getRole();
    }
}
