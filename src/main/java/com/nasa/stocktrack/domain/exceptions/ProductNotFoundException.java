package com.nasa.stocktrack.domain.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException() {
        super("The product is not found");
    }
}
