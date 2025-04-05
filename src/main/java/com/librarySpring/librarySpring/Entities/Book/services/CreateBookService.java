package com.librarySpring.librarySpring.Entities.Book.services;

import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Book.BookErrorMessages;
import com.librarySpring.librarySpring.Entities.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Entities.Book.model.BookDTO;
import com.librarySpring.librarySpring.Entities.Book.validators.BookValidator;
import com.librarySpring.librarySpring.Exceptions.ResourceAlreadyExistsException;
import com.librarySpring.librarySpring.Interfaces.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CreateBookService implements Command<Book, BookDTO> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public CreateBookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public BookDTO execute(Book input) {
        Optional<Book> bookOptional = bookRepository.findById(input.getIsbn());
        if (bookOptional.isPresent()) {
            throw new ResourceAlreadyExistsException(BookErrorMessages.BOOK_ALREADY_EXISTS);
        }
        BookValidator.execute(input);

        Set<Author> authors = new HashSet<>();
        for (Author author : input.getAuthors()) {
            Author existingAuthor = authorRepository.findById(author.getId()).orElse(null);
            if (existingAuthor == null) {
                existingAuthor = authorRepository.save(author);
            }
            authors.add(existingAuthor);
        }
        input.setAuthors(authors);
        bookRepository.save(input);
        return new BookDTO(input);
    }
}
