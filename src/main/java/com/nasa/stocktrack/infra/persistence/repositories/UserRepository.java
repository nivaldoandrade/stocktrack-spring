package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String username);
}
