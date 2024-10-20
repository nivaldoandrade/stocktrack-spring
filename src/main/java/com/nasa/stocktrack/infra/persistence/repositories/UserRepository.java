package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u FROM UserEntity u " +
            "WHERE LOWER(u.full_name) " +
            "LIKE CONCAT('%', LOWER(:name), '%') " +
            "OR :name IS NULL")
    Page<UserEntity> findAll(@Param("name") String name, Pageable pageable);

    Optional<UserEntity> findByUsername(String username);
}
