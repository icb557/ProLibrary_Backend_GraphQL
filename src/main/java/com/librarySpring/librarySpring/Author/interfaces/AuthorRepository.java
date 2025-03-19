package com.librarySpring.librarySpring.Author.interfaces;

import com.librarySpring.librarySpring.Author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, String> {

}
