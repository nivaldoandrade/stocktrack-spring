package com.nasa.stocktrack.domain.entities;

import java.util.UUID;

public class Warehouse {

    private UUID id;

    private String name;

    public Warehouse(String name) {
        this.name = name;
    }

    public Warehouse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
