package com.librarySpring.librarySpring.Book.services;

import com.librarySpring.librarySpring.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Book.model.Book;
import com.librarySpring.librarySpring.Book.model.BookDTO;
import com.librarySpring.librarySpring.Book.model.UpdateBookCommand;
import com.librarySpring.librarySpring.Book.validators.BookValidator;
import com.librarySpring.librarySpring.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateBookService implements Command<UpdateBookCommand, BookDTO> {

    private final BookRepository bookRepository;

    public UpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<BookDTO> execute(UpdateBookCommand input) {
        Optional<Book> bookOptional = bookRepository.findById(input.getIsbn());
        if (bookOptional.isPresent()) {
            Book book = input.getBook();
            book.setIsbn(input.getIsbn());
            BookValidator.execute(book);
            bookRepository.save(book);
            return ResponseEntity.ok(new BookDTO(book));
        }
        return null;
        //throw exception
    }
}
