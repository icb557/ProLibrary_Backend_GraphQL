package com.librarySpring.librarySpring.Entities.Author.services;

import com.librarySpring.librarySpring.Entities.Author.AuthorErrorMessages;
import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Entities.Author.validators.AuthorValidator;
import com.librarySpring.librarySpring.Exceptions.ResourceAlreadyExistsException;
import com.librarySpring.librarySpring.Interfaces.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateAuthorService implements Command<Author, AuthorDTO> {

    private final AuthorRepository authorRepository;

    public CreateAuthorService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public AuthorDTO execute (Author author){
        Optional<Author> authorOptional = authorRepository.findById(author.getId());
        if (authorOptional.isPresent()) {
            throw new ResourceAlreadyExistsException(AuthorErrorMessages.AUTHOR_ALREADY_EXISTS);
        }
        AuthorValidator.execute(author);
        authorRepository.save(author);
        return new AuthorDTO(author);
    }

}
