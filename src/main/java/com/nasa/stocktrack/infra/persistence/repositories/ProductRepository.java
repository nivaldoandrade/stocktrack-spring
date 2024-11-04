package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {

    Optional<ProductEntity> findByName(String name);
    Optional<ProductEntity> findByCode(String code);
    Optional<ProductEntity> findFirstByCategoryId(UUID categoryId);
    @Modifying
    @Query("UPDATE ProductEntity p " +
            "SET p.name = :name, " +
            "p.code = :code, " +
            "p.brand = :brand, " +
            "p.image = :image, " +
            "p.category = :category " +
            "WHERE p.id = :id")
    void updateWithoutWarehouses(
            @Param("name") String name,
            @Param("code") String code,
            @Param("brand") String brand,
            @Param("image") String image,
            @Param("category")CategoryEntity category,
            @Param("id") UUID id
    );
}
