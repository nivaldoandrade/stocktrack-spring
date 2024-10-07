package com.nasa.stocktrack.domain.exceptions;

public class ProductExistsException extends EntityExistsException {

    public ProductExistsException(String message) {
        super(message);
    }

    public ProductExistsException() {
        super("The product is already in use");
    }
}
