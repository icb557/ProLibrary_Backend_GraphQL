package com.librarySpring.librarySpring.Entities.Author.services;

import com.librarySpring.librarySpring.Entities.Author.AuthorErrorMessages;
import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Interfaces.Query;
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
    public AuthorDTO execute(String input) {
        Optional<Author> authorOptional = authorRepository.findById(input);
        if (authorOptional.isPresent()) {
            return new AuthorDTO(authorOptional.get());
        }
        throw new ResourceNotFoundException(AuthorErrorMessages.AUTHOR_NOT_FOUND);
    }
}
