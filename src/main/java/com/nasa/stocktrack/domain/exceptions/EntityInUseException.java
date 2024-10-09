package com.nasa.stocktrack.domain.exceptions;

public class EntityInUseException extends RuntimeException {

    public EntityInUseException(String message) {
        super(message);
    }
}
