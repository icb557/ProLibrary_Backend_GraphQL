package com.librarySpring.librarySpring.Entities.Book.services;

import com.librarySpring.librarySpring.Entities.Book.BookErrorMessages;
import com.librarySpring.librarySpring.Entities.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Entities.Book.model.BookDTO;
import com.librarySpring.librarySpring.Entities.Book.model.UpdateBookCommand;
import com.librarySpring.librarySpring.Entities.Book.validators.BookValidator;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Interfaces.Command;
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
    public BookDTO execute(UpdateBookCommand input) {
        Optional<Book> bookOptional = bookRepository.findById(input.getIsbn());
        if (bookOptional.isPresent()) {
            Book book = input.getBook();
            book.setIsbn(input.getIsbn());
            BookValidator.execute(book);
            bookRepository.save(book);
            return new BookDTO(book);
        }
        throw new ResourceNotFoundException(BookErrorMessages.BOOK_NOT_FOUND);
    }
}
