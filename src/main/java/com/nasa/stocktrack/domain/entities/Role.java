package com.nasa.stocktrack.domain.entities;

import com.nasa.stocktrack.domain.enums.RoleEnum;

import java.util.UUID;

public class Role {

    private UUID id;

    private RoleEnum name;

    private String description;


    public Role(RoleEnum name) {
        this.name = name;
    }

    public Role(UUID id, RoleEnum name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public RoleEnum getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
