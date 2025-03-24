package com.librarySpring.librarySpring.Interfaces;

import org.springframework.http.ResponseEntity;

public interface Command <I, O> {
    ResponseEntity<O> execute(I input);
}
