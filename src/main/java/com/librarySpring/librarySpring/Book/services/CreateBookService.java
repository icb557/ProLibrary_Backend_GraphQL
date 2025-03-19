package com.librarySpring.librarySpring.Book.services;

import com.librarySpring.librarySpring.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Book.model.Book;
import com.librarySpring.librarySpring.Book.model.BookDTO;
import com.librarySpring.librarySpring.Book.validators.BookValidator;
import com.librarySpring.librarySpring.Person.interfaces.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateBookService implements Command<Book, BookDTO> {

    private final BookRepository bookRepository;

    public CreateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<BookDTO> execute(Book input) {
        BookValidator.execute(input);
        bookRepository.save(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BookDTO(input));
    }
}
