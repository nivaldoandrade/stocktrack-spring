package com.nasa.stocktrack.domain.exceptions;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException() {
        super("The role is not found");
    }
}
