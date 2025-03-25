package com.librarySpring.librarySpring.Entities.Book;

import com.librarySpring.librarySpring.Interfaces.ErrorMessage;

public enum BookErrorMessages implements ErrorMessage {
    BOOK_NOT_FOUND("Book not found"),
    BOOK_ALREADY_EXISTS("Book already exists"),
    ISBN_INVALID("Isbn is required and must be 13 characters long"),
    EDITORIAL_REQUIRED("Editorial is required"),
    TILE_REQUIRED("Title is required"),
    GENRE_REQUIRED("Genre is required"),
    PUBLICATION_YEAR_INVALID("Publication year is required and must be a valid year");

    private final String message;

    BookErrorMessages(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
