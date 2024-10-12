package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.ProductWarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouseEntity, UUID> {


    @Query(
            value = "SELECT * FROM product_warehouse pw " +
            "WHERE pw.product_id = :productId AND pw.warehouse_id = :warehouseId",
            nativeQuery = true
    )
    Optional<ProductWarehouseEntity> findByProductIdAndWarehouseId(UUID productId, UUID warehouseId);

}
