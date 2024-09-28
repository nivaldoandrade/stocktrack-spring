package com.nasa.stocktrack.domain.exceptions;

public class EntityExistsException extends RuntimeException {

    public EntityExistsException(String message) {
        super(message);
    }
}
