package com.nasa.stocktrack.domain.exceptions;

public class ProductWarehouseNotFoundException extends EntityNotFoundException {
    public ProductWarehouseNotFoundException() {
        super("The product warehouse is not found");
    }
}
