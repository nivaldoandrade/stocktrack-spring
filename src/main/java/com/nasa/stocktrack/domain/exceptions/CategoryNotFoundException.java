package com.nasa.stocktrack.domain.exceptions;

public class CategoryNotFoundException extends EntityNotFoundException {
    public CategoryNotFoundException() {
        super("The category is not found");
    }
}
