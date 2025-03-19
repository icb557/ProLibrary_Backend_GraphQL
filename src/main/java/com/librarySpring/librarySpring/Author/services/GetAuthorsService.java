package com.librarySpring.librarySpring.Author.services;

import com.librarySpring.librarySpring.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Author.interfaces.Query;
import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAuthorsService implements Query<Void,List<AuthorDTO>> {
    private final AuthorRepository authorRepository;

    public GetAuthorsService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public ResponseEntity<List<AuthorDTO>> execute(Void input){
        List<Author> authors = authorRepository.findAll();
        List<AuthorDTO> authorDTO = authors.stream().map(AuthorDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(authorDTO);
    }

}

