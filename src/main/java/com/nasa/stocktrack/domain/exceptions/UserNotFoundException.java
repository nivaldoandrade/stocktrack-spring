package com.nasa.stocktrack.domain.exceptions;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("The user is not found");
    }
}
