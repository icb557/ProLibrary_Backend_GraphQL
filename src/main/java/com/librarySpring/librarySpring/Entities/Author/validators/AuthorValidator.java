package com.librarySpring.librarySpring.Entities.Author.validators;

import com.librarySpring.librarySpring.Entities.Author.AuthorErrorMessages;
import com.librarySpring.librarySpring.Entities.Author.model.Author;
import com.librarySpring.librarySpring.Exceptions.AttributeNotValidException;

public class AuthorValidator {
    public static void execute(Author author){
        if (author.getId().isBlank() || author.getId().length() < 6  || author.getId().length() > 10  ){
            throw new AttributeNotValidException(AuthorErrorMessages.ID_INVALID);
        }
        if (author.getFirstName().isBlank() ){
            throw new AttributeNotValidException(AuthorErrorMessages.FIRSTNAME_REQUIRED);
        }
        if(author.getFirstLastName().isBlank()){
            throw new AttributeNotValidException(AuthorErrorMessages.FIRSTNAME_REQUIRED);
        }
        if(author.getSecondLastName().isBlank()){
            throw new AttributeNotValidException(AuthorErrorMessages.SECOND_LASTNAME_REQUIRED);
        }
        if(author.getNationality().isBlank()){
            throw new AttributeNotValidException(AuthorErrorMessages.NATIONALITY_REQUIRED);
        }

    }
}
