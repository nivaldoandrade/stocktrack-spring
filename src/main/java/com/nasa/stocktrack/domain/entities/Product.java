package com.nasa.stocktrack.domain.entities;

import java.util.UUID;

public class Product {

    private UUID id;

    private String name;

    private String code;

    private String brand;

    private Category category;

    public Product(String name, String code, String brand, Category category) {
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.category = category;
    }

    public Product(UUID id, String name, String code, String brand, Category category) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getBrand() {
        return brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
