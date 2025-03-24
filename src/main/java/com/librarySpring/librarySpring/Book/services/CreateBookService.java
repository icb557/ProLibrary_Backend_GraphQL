package com.librarySpring.librarySpring.Book.services;

import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Book.model.Book;
import com.librarySpring.librarySpring.Book.model.BookDTO;
import com.librarySpring.librarySpring.Book.validators.BookValidator;
import com.librarySpring.librarySpring.Person.interfaces.Command;
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
    public ResponseEntity<BookDTO> execute(Book input) {
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
        return ResponseEntity.status(HttpStatus.CREATED).body(new BookDTO(input));
    }
}
