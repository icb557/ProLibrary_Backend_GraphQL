package com.librarySpring.librarySpring.Entities.Book;

import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Entities.Author.services.GetAuthorService;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Entities.Book.model.BookDTO;
import com.librarySpring.librarySpring.Entities.Book.model.UpdateBookCommand;
import com.librarySpring.librarySpring.Entities.Book.services.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;

@Controller
public class BookController {

    private final CreateBookService createBookService;
    private final GetBooksService getBooksService;
    private final GetBookService getBookService;
    private final SearchBookService searchBookService;
    private final UpdateBookService updateBookService;
    private final DeleteBookService deleteBookService;
    private final GetAuthorService getAuthorService;

    public BookController(CreateBookService createBookService, GetBooksService getBooksService, GetBookService getBookService, SearchBookService searchBookService, UpdateBookService updateBookService, DeleteBookService deleteBookService, GetAuthorService getAuthorService) {
        this.createBookService = createBookService;
        this.getBooksService = getBooksService;
        this.getBookService = getBookService;
        this.searchBookService = searchBookService;
        this.updateBookService = updateBookService;
        this.deleteBookService = deleteBookService;
        this.getAuthorService = getAuthorService;
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BookDTO createBook(@Argument Book book) {
        return createBookService.execute(book);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<BookDTO> getBooks() {
        return getBooksService.execute(null);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public BookDTO getBookById(@Argument String isbn) {
        return getBookService.execute(isbn);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<BookDTO> searchBookByTitle(@Argument String title) {
        return searchBookService.execute(title);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public BookDTO updateBook(@Argument String isbn, @Argument Book book) {
        System.out.println(book.toString());
        return updateBookService.execute(new UpdateBookCommand(isbn, book));
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteBook(@Argument String isbn) {
        return deleteBookService.execute(isbn);
    }

    @SchemaMapping(typeName = "Book", field = "authors")
    public Set<AuthorDTO> getAuthorsForBook(BookDTO book) {
        return getBookService.execute(book.getIsbn()).getAuthors();
    }
}
