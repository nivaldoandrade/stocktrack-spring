package com.nasa.stocktrack.domain.entities;

import java.util.UUID;

public class Warehouse {

    private UUID id;

    private String name;

    public Warehouse(UUID id) {
        this.id = id;
    }

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

    public void setName(String name) {
        this.name = name;
    }
}
