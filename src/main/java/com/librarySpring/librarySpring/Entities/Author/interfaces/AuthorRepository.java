package com.librarySpring.librarySpring.Entities.Author.interfaces;

import com.librarySpring.librarySpring.Entities.Author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository <Author, String> {

}
