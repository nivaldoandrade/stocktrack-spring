package com.nasa.stocktrack.infra.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

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

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp(source = SourceType.DB)
    private Instant updatedAt;

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
