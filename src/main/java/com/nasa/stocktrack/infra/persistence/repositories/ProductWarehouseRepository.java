package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.ProductWarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductWarehouseRepository extends JpaRepository<ProductWarehouseEntity, UUID> {
}
