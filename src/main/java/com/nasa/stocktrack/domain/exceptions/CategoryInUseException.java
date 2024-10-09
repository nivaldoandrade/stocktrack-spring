package com.nasa.stocktrack.domain.exceptions;

public class CategoryInUseException extends EntityInUseException {
    public CategoryInUseException() {
        super( "Cannot delete the category as it is being used by one or more products");
    }
}
