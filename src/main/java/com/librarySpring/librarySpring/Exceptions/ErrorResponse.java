package com.librarySpring.librarySpring.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String status;
    private final String path;
}
