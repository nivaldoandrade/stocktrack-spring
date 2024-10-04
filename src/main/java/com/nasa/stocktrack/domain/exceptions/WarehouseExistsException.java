package com.nasa.stocktrack.domain.exceptions;

public class WarehouseExistsException extends EntityExistsException {

    public WarehouseExistsException() {
        super("The warehouse is already in use");
    }
}
