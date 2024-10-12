package com.nasa.stocktrack.infra.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "product_warehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductWarehouseEntity {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private ProductWarehousePKEntity id = new ProductWarehousePKEntity();

    private Integer quantity;

    private String location;

    public ProductWarehouseEntity(
            ProductEntity productEntity,
            WarehouseEntity warehouseEntity,
            Integer quantity,
            String location
    ) {
        this.id.setProductEntity(productEntity);
        this.id.setWarehouseEntity(warehouseEntity);
        this.quantity = quantity;
        this.location = location;
    }

    @JsonIgnore
    public ProductEntity getProductEntity() {
        return id.getProductEntity();
    }

    public void setProductEntity(ProductEntity productEntity) {
        id.setProductEntity(productEntity);
    }

    public WarehouseEntity getWarehouseEntity() {
        return id.getWarehouseEntity();
    }

    public void setWarehouseEntity(WarehouseEntity warehouseEntity) {
        id.setWarehouseEntity(warehouseEntity);
    }
}
