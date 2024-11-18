package com.nasa.stocktrack.domain.exceptions;

public class SelfDeletionException extends RuntimeException {

    public SelfDeletionException() {
        super("You cant delete yourself");
    }

}
