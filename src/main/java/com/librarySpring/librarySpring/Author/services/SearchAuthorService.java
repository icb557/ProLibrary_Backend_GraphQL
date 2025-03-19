package com.librarySpring.librarySpring.Author.services;

import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.interfaces.Query;
import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchAuthorService implements Query<String, AuthorDTO> {
    private final AuthorRepository  authorRepository;

    public SearchAuthorService (AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public ResponseEntity<AuthorDTO> execute(String input){
        Optional<Author> author = authorRepository.findById(input);
        return ResponseEntity.ok(new AuthorDTO(author));
    }
}
