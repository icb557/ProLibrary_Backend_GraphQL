package com.librarySpring.librarySpring.Interfaces;

import org.springframework.http.ResponseEntity;

public interface Command <I, O> {
    O execute(I input);
}
