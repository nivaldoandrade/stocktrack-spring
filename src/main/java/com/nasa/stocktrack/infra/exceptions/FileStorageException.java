package com.nasa.stocktrack.infra.exceptions;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }
}
