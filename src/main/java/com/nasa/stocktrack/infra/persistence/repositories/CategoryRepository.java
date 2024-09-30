package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {


    @Query("SELECT c FROM CategoryEntity c " +
            "WHERE LOWER(c.name) " +
            "LIKE CONCAT('%', LOWER(:name), '%') " +
            "OR :name IS NULL"
    )
    Page<CategoryEntity> findAll(@Param("name") String name, Pageable pageable);

    Optional<CategoryEntity> findByName(String name);
}
