package com.nasa.stocktrack.infra.exceptions;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String filename) {
        super("File not found: " + filename);
    }
}
