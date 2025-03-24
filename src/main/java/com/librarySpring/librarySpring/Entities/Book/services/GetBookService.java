package com.librarySpring.librarySpring.Entities.Book.services;

import com.librarySpring.librarySpring.Entities.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Interfaces.Query;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Entities.Book.model.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetBookService implements Query<String, BookDTO> {

    private final BookRepository bookRepository;

    public GetBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<BookDTO> execute(String input) {
        Optional<Book> bookOptional = bookRepository.findById(input);
        if (bookOptional.isPresent()) {
            return ResponseEntity.ok(new BookDTO(bookOptional.get()));
        }

        return null;
        //throw exception
    }
}
