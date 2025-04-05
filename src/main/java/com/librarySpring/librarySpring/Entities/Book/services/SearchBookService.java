package com.librarySpring.librarySpring.Entities.Book.services;

import com.librarySpring.librarySpring.Entities.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Interfaces.Query;
import com.librarySpring.librarySpring.Entities.Book.model.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchBookService implements Query<String, List<BookDTO>> {

    private final BookRepository bookRepository;

    public SearchBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDTO> execute(String input) {
        return bookRepository.findByTitleContainingIgnoreCase(input)
                .stream()
                .map(BookDTO::new)
                .toList();
    }
}
