package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.User;

public interface UserGateway {
    User create(User user);

    User findByUsername(String username);
}
