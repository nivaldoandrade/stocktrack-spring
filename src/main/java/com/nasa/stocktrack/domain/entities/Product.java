package com.nasa.stocktrack.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {

    private UUID id;

    private String name;

    private String code;

    private String brand;

    private Category category;

    private List<ProductWarehouse> productWarehouses = new ArrayList<>();

    public Product(UUID id) {
        this.id = id;
    }

    public Product(UUID id, String name, String code, String brand, Category category, List<ProductWarehouse> productWarehouses) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.category = category;
        this.productWarehouses = productWarehouses;
    }

    public Product(String name, String code, String brand, Category category, List<ProductWarehouse> productWarehouses) {
        this.name = name;
        this.code = code;
        this.brand = brand;
        this.category = category;
        this.productWarehouses = productWarehouses;
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

    public List<ProductWarehouse> getProductWarehouses() {
        return productWarehouses;
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

    public void setProductWarehouses(List<ProductWarehouse> productWarehouses) {
        this.productWarehouses = productWarehouses;
    }
}
