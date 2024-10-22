package com.nasa.stocktrack.domain.enums;

public enum RoleEnum {
    USER,
    ADMIN;

    public static RoleEnum fromString(String value) {
        if(value != null) {
            for (RoleEnum role: RoleEnum.values()) {
                if(value.equalsIgnoreCase(role.name())) {
                    return role;
                }
            }
        }

        throw new IllegalArgumentException("No constant with name " + value + " found");
    }
}
