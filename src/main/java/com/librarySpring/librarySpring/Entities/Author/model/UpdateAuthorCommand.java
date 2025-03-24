package com.librarySpring.librarySpring.Entities.Author.model;

public class UpdateAuthorCommand {
    private final String id;
    private final Author author;

    public UpdateAuthorCommand(String id , Author author){
        this.id = id;
        this.author = author;
    }

    public String getId() {return id ;}

    public Author getAuthor() {return author;}
}
