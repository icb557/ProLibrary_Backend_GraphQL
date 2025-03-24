package com.librarySpring.librarySpring.Entities.Book.services;

import com.librarySpring.librarySpring.Entities.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Interfaces.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteBookService implements Command<String, Void> {

    private final BookRepository bookRepository;

    public DeleteBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<Void> execute(String input) {
        Optional<Book> bookOptional = bookRepository.findById(input);
        if (bookOptional.isPresent()) {
            bookRepository.deleteById(input);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
        //throw exception
    }
}
