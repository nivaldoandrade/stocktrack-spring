package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, UUID> {

    @Query("SELECT w FROM WarehouseEntity w " +
            "WHERE LOWER(w.name) " +
            "LIKE CONCAT('%', LOWER(:name), '%') " +
            "OR :name IS NULL")
    Page<WarehouseEntity> findAll(@Param("name") String name, Pageable pageable);
    Optional<WarehouseEntity> findByName(String name);
}
