package com.librarySpring.librarySpring.Book.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Year;

@Entity
@Table(name="books")
@Data
public class Book {

    @Id
    @Size(min = 13, max = 13)
    @Column(name = "isbn")
    private String isbn;

    @NotNull
    @Column(name="title")
    private String title;

    @NotNull
    @Column(name = "editorial")
    private String editorial;

    @NotNull
    @Column(name = "genre")
    private String genre;

    @NotNull
    @Positive
    @Min(1)
    @Column(name = "publicationYear")
    private Integer publicationYear;

}

