package com.librarySpring.librarySpring.Interfaces;

import org.springframework.http.ResponseEntity;

public interface Query <I, O> {
    O execute(I input);
}
