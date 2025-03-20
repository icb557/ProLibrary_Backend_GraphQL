package com.librarySpring.librarySpring.Author.validators;

import com.librarySpring.librarySpring.Author.model.Author;

public class AuthorValidator {
    public static void execute(Author author){
        if (author.getId().isBlank() || author.getId().length() < 6  || author.getId().length() > 10  ){
            throw new IllegalArgumentException("Id  it's empty or does not meet the requirements ");
        }
        if (author.getFirstName().isBlank() ){
            throw new IllegalArgumentException("FirstName Cannot be empty");
        }
        if(author.getFirstLastName().isBlank()){
            throw new IllegalArgumentException("Firs last name Cannot be empty");
        }
        if(author.getMiddleLastName().isBlank()){
            throw new IllegalArgumentException("Middle name  Cannot be empty");
        }

    }
}
