package com.librarySpring.librarySpring.Author.services;

import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetAuthorService implements Query<String, AuthorDTO> {
    private final AuthorRepository authorRepository;

    public GetAuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public ResponseEntity<AuthorDTO> execute(String input) {
        Optional<Author> authorOptional = authorRepository.findById(input);
        if (authorOptional.isPresent()) {
            return ResponseEntity.ok(new AuthorDTO(authorOptional.get()));
        }
        return null;
    }
}
