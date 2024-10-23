package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.infra.persistence.entities.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {


    RoleEntity toEntity(Role role) {
        return new RoleEntity(
                role.getId(),
                role.getName(),
                role.getDescription()
        );
    }

    Role toDomain(RoleEntity roleEntity) {
        return new Role(
                roleEntity.getId(),
                roleEntity.getName(),
                roleEntity.getDescription()
        );
    }
}
