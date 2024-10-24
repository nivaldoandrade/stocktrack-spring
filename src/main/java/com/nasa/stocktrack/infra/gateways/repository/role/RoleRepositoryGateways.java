package com.nasa.stocktrack.infra.gateways.repository.role;

import com.nasa.stocktrack.application.gateways.RoleGateway;
import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.infra.persistence.entities.RoleEntity;
import com.nasa.stocktrack.infra.persistence.repositories.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepositoryGateways implements RoleGateway {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;


    public RoleRepositoryGateways(
            RoleRepository roleRepository,
            RoleMapper roleMapper
    ) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Role findByName(RoleEnum name) {
        Optional<RoleEntity> roleEntity = roleRepository.findByName(name);

        return roleEntity.map(roleMapper::toDomain).orElse(null);
    }
}
