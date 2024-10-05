package com.nasa.stocktrack.domain.exceptions;

public class WarehouseNotFoundException extends EntityNotFoundException {
    public WarehouseNotFoundException() {
        super("The warehouse is not found");
    }
}
