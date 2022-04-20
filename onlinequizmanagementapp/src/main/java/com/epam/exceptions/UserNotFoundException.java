package com.epam.exceptions;

import org.springframework.security.authentication.BadCredentialsException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, BadCredentialsException e) {
        super(message,e);
    }
}
