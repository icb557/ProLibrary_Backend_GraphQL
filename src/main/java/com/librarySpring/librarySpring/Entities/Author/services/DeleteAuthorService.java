package com.librarySpring.librarySpring.Entities.Author.services;

import com.librarySpring.librarySpring.Entities.Author.AuthorErrorMessages;
import com.librarySpring.librarySpring.Entities.Author.interfaces.AuthorRepository;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Book.model.Book;
import com.librarySpring.librarySpring.Exceptions.CannotDeleteResource;
import com.librarySpring.librarySpring.Exceptions.ResourceNotFoundException;
import com.librarySpring.librarySpring.Interfaces.Command;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DeleteAuthorService implements Command<String, Void> {
    private final AuthorRepository authorRepository;

    public DeleteAuthorService(AuthorRepository authorRepository){this.authorRepository = authorRepository;}

    @Override
    public Void execute(String input){
        Optional<Author> authorOptional = authorRepository.findById(input);
        if(authorOptional.isPresent()){
            Set<Book> books = authorOptional.get().getBooks();
            if (!books.isEmpty()) {
                throw new CannotDeleteResource(AuthorErrorMessages.CANNOT_DELETE_AUTHOR);
            }
            authorRepository.deleteById(input);
        }
        throw new ResourceNotFoundException(AuthorErrorMessages.AUTHOR_NOT_FOUND);
    }
}
