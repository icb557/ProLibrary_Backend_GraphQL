package com.librarySpring.librarySpring.Author.services;


import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.interfaces.Command;
import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Author.model.UpdateAuthorCommand;
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
