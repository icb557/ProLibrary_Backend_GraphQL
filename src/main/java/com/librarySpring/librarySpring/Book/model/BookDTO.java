package com.librarySpring.librarySpring.Book.model;

import lombok.Data;

@Data
public class BookDTO {
    private String isbn;
    private String title;
    private String editorial;
    private String genre;
    private Integer publicationYear;

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.editorial = book.getEditorial();
        this.genre = book.getGenre();
        this.publicationYear = book.getPublicationYear();
    }
}
