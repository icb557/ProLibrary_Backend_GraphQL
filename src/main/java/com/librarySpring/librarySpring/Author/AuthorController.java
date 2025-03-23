package com.librarySpring.librarySpring.Author;

import com.librarySpring.librarySpring.Author.model.Author;
import com.librarySpring.librarySpring.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Author.model.UpdateAuthorCommand;
import com.librarySpring.librarySpring.Author.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {
    private final CreateAuthorService createAuthorService;
    private final GetAuthorsService getAuthorsService;
    private final GetAuthorService getAuthorService;
    private final UpdateAuthorService updateAuthorService;
    private final DeleteAuthorService deleteAuthorService;

    public AuthorController (CreateAuthorService createAuthorService, GetAuthorsService getAuthorsService
    , GetAuthorService getAuthorService,UpdateAuthorService updateAuthorService
    , DeleteAuthorService deleteAuthorService){
        this.createAuthorService = createAuthorService;
        this.getAuthorsService = getAuthorsService;
        this.getAuthorService = getAuthorService;
        this.updateAuthorService = updateAuthorService;
        this.deleteAuthorService = deleteAuthorService;

    }
    @PostMapping("/author")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
        return createAuthorService.execute(author);
    }

    @GetMapping("/authors")
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        return getAuthorsService.execute(null);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable String id) {
        return getAuthorService.execute(id);
    }


    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable String id, @RequestBody Author author) {
        return updateAuthorService.execute(new UpdateAuthorCommand(id, author));
    }

    @DeleteMapping("/author/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable String id) {
        return deleteAuthorService.execute(id);
    }

}
