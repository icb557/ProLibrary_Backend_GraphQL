package com.librarySpring.librarySpring.Book.validators;

import com.librarySpring.librarySpring.Book.model.Book;

import java.time.Year;

public class BookValidator {

    public BookValidator() {}

    public static void execute(Book book) {
        if (book.getIsbn().isBlank() || book.getIsbn().length() != 13) {
            throw new IllegalArgumentException("Isbn is required and must be 13 characters long");
        }
        if (book.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (book.getEditorial().isBlank()) {
            throw new IllegalArgumentException("Editorial is required");
        }
        if (book.getGenre().isBlank()) {
            throw new IllegalArgumentException("Genre is required");
        }
        if (book.getPublicationYear() == null || book.getPublicationYear() < 0 || book.getPublicationYear() > Year.now().getValue()) {
            throw new IllegalArgumentException("Publication year is required and must be a valid year");
        }
    }
}
