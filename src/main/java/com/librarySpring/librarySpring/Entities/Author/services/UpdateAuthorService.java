package com.librarySpring.librarySpring.Entities.Author.services;


import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Entities.Author.model.UpdateAuthorCommand;
import com.librarySpring.librarySpring.Entities.Author.validators.AuthorValidator;
import com.librarySpring.librarySpring.Interfaces.Command;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateAuthorService implements Command<UpdateAuthorCommand, AuthorDTO> {
    private final AuthorRepository authorRepository;

    public UpdateAuthorService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public ResponseEntity<AuthorDTO> execute(UpdateAuthorCommand input){
        Optional<Author> authorOptional = authorRepository.findById(input.getId());
        if(authorOptional.isPresent()){
            Author author = input.getAuthor();
            author.setId(input.getId());
            AuthorValidator.execute(author);
            authorRepository.save(author);
            return ResponseEntity.ok(new AuthorDTO(author));
        }
        return null;
    }

}
