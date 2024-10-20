package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.User;

import java.util.UUID;

public interface UserGateway {
    User create(User user);

    User findByUsername(String username);

    User findById(UUID id);
}
