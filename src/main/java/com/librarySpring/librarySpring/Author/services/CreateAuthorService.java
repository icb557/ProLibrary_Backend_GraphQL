package com.librarySpring.librarySpring.Author.services;

import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateAuthorService implements Command<Author, AuthorDTO> {

    private final AuthorRepository authorRepository;

    public CreateAuthorService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public ResponseEntity<AuthorDTO> execute (Author author){
        Optional<Author> authorOptional = authorRepository.findById(author.getId());
        if (authorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthorDTO(author));
    }

}
