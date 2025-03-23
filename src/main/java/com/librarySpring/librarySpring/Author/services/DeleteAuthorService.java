package com.librarySpring.librarySpring.Author.services;

import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.interfaces.Command;
import com.librarySpring.librarySpring.Author.model.Author;
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
