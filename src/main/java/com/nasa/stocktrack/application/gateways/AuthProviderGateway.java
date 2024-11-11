package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.User;

public interface AuthProviderGateway {
    User authenticate(String username, String password);
}
