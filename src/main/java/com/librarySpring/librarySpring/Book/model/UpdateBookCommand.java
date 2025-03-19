package com.librarySpring.librarySpring.Book.model;

public class UpdateBookCommand {
    private String isbn;
    private Book book;

    public UpdateBookCommand(String isbn, Book book) {
        this.isbn = isbn;
        this.book = book;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book getBook() {
        return book;
    }
}
