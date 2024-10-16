package com.nasa.stocktrack.domain.exceptions;

public class UserInUseException extends EntityInUseException {
    public UserInUseException() {
        super("Username already in use");
    }
}
