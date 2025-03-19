package com.librarySpring.librarySpring.Author.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="Author")
@Data
public class Author {

    @Id
    @Column(name="id")
    private String id;

    @NotNull
    @Column(name="firstName")
    private String firstName;


    @Column(name="middleName")
    private String middleName;

    @NotNull
    @Column(name="firstLastName")
    private String firstLastName;

    @Column(name="middleLastName")
    private String middleLastName;

    @Column(name="nationality")
    private String nationality;

}
