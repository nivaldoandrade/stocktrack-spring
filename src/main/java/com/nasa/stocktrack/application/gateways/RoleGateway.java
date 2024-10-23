package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.domain.enums.RoleEnum;

public interface RoleGateway {

    Role findByName(RoleEnum name);
}
