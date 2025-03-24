package com.librarySpring.librarySpring.Entities.Book.model;

import com.librarySpring.librarySpring.Entities.Author.model.Author;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @Column(name = "isbn", length = 13)
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_isbn", referencedColumnName = "isbn"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Author> authors;

}

