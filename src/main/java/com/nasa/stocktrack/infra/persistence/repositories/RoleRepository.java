package com.nasa.stocktrack.infra.persistence.repositories;

import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.infra.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    Optional<RoleEntity> findByName(RoleEnum name);
}
