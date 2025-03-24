package com.librarySpring.librarySpring.Entities.Author.services;

import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Interfaces.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteAuthorService implements Command<String, Void> {
    private final AuthorRepository authorRepository;

    public DeleteAuthorService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public ResponseEntity<Void> execute(String input){
        Optional<Author> authorOptional = authorRepository.findById(input);
        if(authorOptional.isPresent()){
            authorRepository.deleteById(input);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
