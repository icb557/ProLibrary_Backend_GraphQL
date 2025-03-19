package com.librarySpring.librarySpring.Book.services;

import com.librarySpring.librarySpring.Book.interfaces.BookRepository;
import com.librarySpring.librarySpring.Book.interfaces.Query;
import com.librarySpring.librarySpring.Book.model.Book;
import com.librarySpring.librarySpring.Book.model.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetBooksService implements Query<Book, List<BookDTO>> {

    private final BookRepository bookRepository;

    public GetBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<List<BookDTO>> execute(Book input) {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOs = books.stream().map(BookDTO::new).toList();
        return ResponseEntity.ok(bookDTOs);
    }
}
