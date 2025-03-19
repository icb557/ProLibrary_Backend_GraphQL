package com.librarySpring.librarySpring.Author.model;
import lombok.Data;

import java.util.Optional;

@Data
public class AuthorDTO {
    private String id;
    private String firstName;
    private String middleName;
    private String firstLastName;
    private String middleLastName;
    private String nationality;

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.firstName = author.getFirstName();
        this.middleName = author.getMiddleName();
        this.firstLastName = author.getFirstLastName();
        this.middleLastName = author.getMiddleLastName();
        this.nationality = author.getNationality();
    }
}
