package com.nasa.stocktrack.domain.exceptions;

public class CategoryExistsException extends EntityExistsException {

    public CategoryExistsException() {
        super("The category is already in use");
    }
}
