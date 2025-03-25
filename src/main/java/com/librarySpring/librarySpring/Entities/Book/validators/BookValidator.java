package com.librarySpring.librarySpring.Entities.Book.validators;

import com.librarySpring.librarySpring.Entities.Book.BookErrorMessages;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Exceptions.AttributeNotValidException;

import java.time.Year;

public class BookValidator {

    public BookValidator() {}

    public static void execute(Book book) {
        if (book.getIsbn().isBlank() || book.getIsbn().length() != 13) {
            throw new AttributeNotValidException(BookErrorMessages.ISBN_INVALID);
        }
        if (book.getTitle().isBlank()) {
            throw new AttributeNotValidException(BookErrorMessages.TILE_REQUIRED);
        }
        if (book.getEditorial().isBlank()) {
            throw new AttributeNotValidException(BookErrorMessages.EDITORIAL_REQUIRED);
        }
        if (book.getGenre().isBlank()) {
            throw new AttributeNotValidException(BookErrorMessages.GENRE_REQUIRED);
        }
        if (book.getPublicationYear() == null || book.getPublicationYear() < 0 || book.getPublicationYear() > Year.now().getValue()) {
            throw new AttributeNotValidException(BookErrorMessages.PUBLICATION_YEAR_INVALID);
        }
    }
}
