package com.nasa.stocktrack.infra.persistence.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode(of = {"productEntity", "warehouseEntity"})
public class ProductWarehousePKEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouseEntity;
}
