package com.nasa.stocktrack.domain.entities;

import com.nasa.stocktrack.domain.entities.PK.ProductWarehousePK;

public class ProductWarehouse {

    private ProductWarehousePK id = new ProductWarehousePK();

    private Integer quantity;

    private String location;

    public ProductWarehouse(Product product, Warehouse warehouse) {
        this.id.setProduct(product);
        this.id.setWarehouse(warehouse);
    }

    public ProductWarehouse(Product product, Warehouse warehouse, Integer quantity, String location) {
        this.id.setProduct(product);
        this.id.setWarehouse(warehouse);
        this.quantity = quantity;
        this.location = location;
    }

    public ProductWarehouse(Warehouse warehouse, Integer quantity, String location) {
        this.id.setWarehouse(warehouse);
        this.quantity = quantity;
        this.location = location;
    }

    public Warehouse getWarehouse() {
        return id.getWarehouse();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }
}
