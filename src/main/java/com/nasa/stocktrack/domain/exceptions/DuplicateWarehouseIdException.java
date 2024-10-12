package com.nasa.stocktrack.domain.exceptions;

public class DuplicateWarehouseIdException extends RuntimeException {

    private final String pointer;

    private final String reason;

    public DuplicateWarehouseIdException(String pointer, String reason) {
        this.pointer = pointer;
        this.reason = reason;
    }

    public String getPointer() {
        return pointer;
    }

    public String getReason() {
        return reason;
    }
}
