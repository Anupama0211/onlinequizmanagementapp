package com.epam.exceptions;

public class InvalidIDException extends RuntimeException {
    public InvalidIDException(String message) {
        super(message);
    }
}
