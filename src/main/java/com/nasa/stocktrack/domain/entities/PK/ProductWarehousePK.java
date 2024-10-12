package com.nasa.stocktrack.domain.entities.PK;

import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.Warehouse;

public class ProductWarehousePK {

    private Product product;

    private Warehouse warehouse;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
