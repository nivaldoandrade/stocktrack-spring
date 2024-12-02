package com.nasa.stocktrack.infra.bootstrap;

import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.infra.persistence.entities.RoleEntity;
import com.nasa.stocktrack.infra.persistence.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Order(1)
@Transactional
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    private static final List<RoleEnum> ROLE_ENUMS = List.of(RoleEnum.USER, RoleEnum.ADMIN);

    private static final Map<RoleEnum, String> ROLE_DESCRIPTION_MAP = Map.of(
            RoleEnum.USER, "User role",
            RoleEnum.ADMIN, "Admin role"
    );

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadRoles();
    }

    private void loadRoles() {
        ROLE_ENUMS.forEach(role -> {
            Optional<RoleEntity> roleEntity = roleRepository.findByName(role);

            if(roleEntity.isPresent()) {
                return;
            }

            RoleEntity newRole = new RoleEntity();

            newRole.setName(role);
            newRole.setDescription(ROLE_DESCRIPTION_MAP.get(role));

            roleRepository.save(newRole);
        });
    }
}
