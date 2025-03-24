package com.librarySpring.librarySpring.Book.model;

import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class BookDTO {
    private String isbn;
    private String title;
    private String editorial;
    private String genre;
    private Integer publicationYear;
    private Set<AuthorDTO> authors;

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.editorial = book.getEditorial();
        this.genre = book.getGenre();
        this.publicationYear = book.getPublicationYear();
        this.authors = book.getAuthors().stream().map(AuthorDTO::new).collect(Collectors.toSet());
    }
}
