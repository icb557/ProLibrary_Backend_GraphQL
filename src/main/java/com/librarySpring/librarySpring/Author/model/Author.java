package com.librarySpring.librarySpring.Author.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.librarySpring.librarySpring.Book.model.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name="secondLastName")
    private String secondLastName;

    @Column(name="nationality")
    private String nationality;

    @ManyToMany(mappedBy = "authors" ,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Book> books;

}
