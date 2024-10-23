package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.RoleGateway;
import com.nasa.stocktrack.application.services.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleDependencyInjection {

    @Bean
    RoleService roleService(RoleGateway roleGateway) {
        return new RoleService(roleGateway);
    }
}
