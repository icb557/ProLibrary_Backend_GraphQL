package com.librarySpring.librarySpring.Entities.Author;

import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Entities.Author.model.AuthorDTO;
import com.librarySpring.librarySpring.Entities.Author.model.UpdateAuthorCommand;
import com.librarySpring.librarySpring.Entities.Author.services.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AuthorDTO createAuthor(@Argument Author author) {
        return createAuthorService.execute(author);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public List<AuthorDTO> getAuthors() {
        return getAuthorsService.execute(null);
    }

    @QueryMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public AuthorDTO getAuthorById(@Argument String id) {
        return getAuthorService.execute(id);
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public AuthorDTO updateAuthor(@Argument String id, @Argument Author author) {
        return updateAuthorService.execute(new UpdateAuthorCommand(id, author));
    }

    @MutationMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Boolean deleteAuthor(@Argument String id) {
        return deleteAuthorService.execute(id);
    }

}
