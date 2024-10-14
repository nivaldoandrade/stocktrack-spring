package com.nasa.stocktrack.domain.exceptions;

public class WarehouseInUseException extends EntityInUseException {
    public WarehouseInUseException() {
        super( "Cannot delete the warehouse as it is being used by one or more products");
    }
}
