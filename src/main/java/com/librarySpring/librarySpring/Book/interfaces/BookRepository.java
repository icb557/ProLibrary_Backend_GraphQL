package com.librarySpring.librarySpring.Book.interfaces;

import com.librarySpring.librarySpring.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findByTitleContainingIgnoreCase(String title);

}
