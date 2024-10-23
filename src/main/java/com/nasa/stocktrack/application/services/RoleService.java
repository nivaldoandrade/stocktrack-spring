package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.RoleGateway;
import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.domain.exceptions.RoleNotFoundException;

public class RoleService {

    private final RoleGateway roleGateway;

    public RoleService(RoleGateway roleGateway) {
        this.roleGateway = roleGateway;
    }

    public Role getRole(RoleEnum name) {
        Role role = roleGateway.findByName(name);

        if(role == null) {
            throw new RoleNotFoundException();
        }

        return role;
    }
}
