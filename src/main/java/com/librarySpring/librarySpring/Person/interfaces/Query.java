package com.librarySpring.librarySpring.Person.interfaces;

import org.springframework.http.ResponseEntity;

public interface Query <I, O> {
    ResponseEntity<O> execute(I input);
}
