package com.librarySpring.librarySpring.Book;

import com.librarySpring.librarySpring.Book.model.Book;
import com.librarySpring.librarySpring.Book.model.BookDTO;
import com.librarySpring.librarySpring.Book.model.UpdateBookCommand;
import com.librarySpring.librarySpring.Book.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final CreateBookService createBookService;
    private final GetBooksService getBooksService;
    private final GetBookService getBookService;
    private final SearchBookService searchBookService;
    private final UpdateBookService updateBookService;
    private final DeleteBookService deleteBookService;

    public BookController(CreateBookService createBookService, GetBooksService getBooksService, GetBookService getBookService, SearchBookService searchBookService, UpdateBookService updateBookService, DeleteBookService deleteBookService) {
        this.createBookService = createBookService;
        this.getBooksService = getBooksService;
        this.getBookService = getBookService;
        this.searchBookService = searchBookService;
        this.updateBookService = updateBookService;
        this.deleteBookService = deleteBookService;
    }

    @PostMapping("/book")
    public ResponseEntity<BookDTO> createBook(@RequestBody Book book) {
        return createBookService.execute(book);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getBooks() {
        return getBooksService.execute(null);
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable String isbn) {
        return getBookService.execute(isbn);
    }

    @GetMapping("/book/search")
    public ResponseEntity<List<BookDTO>> searchBookByUsername(@RequestParam String title) {
        return searchBookService.execute(title);
    }

    @PutMapping("/book/{isbn}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        return updateBookService.execute(new UpdateBookCommand(isbn, book));
    }

    @DeleteMapping("/book/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        return deleteBookService.execute(isbn);
    }
}
