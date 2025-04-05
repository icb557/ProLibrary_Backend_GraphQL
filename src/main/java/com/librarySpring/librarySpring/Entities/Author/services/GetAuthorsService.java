package com.librarySpring.librarySpring.Entities.Author.services;

import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Interfaces.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAuthorsService implements Query<Void,List<AuthorDTO>> {
    private final AuthorRepository authorRepository;

    public GetAuthorsService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public List<AuthorDTO> execute(Void input){
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTO = authors.stream().map(AuthorDTO::new).toList();
        return authorDTO;
    }

}

