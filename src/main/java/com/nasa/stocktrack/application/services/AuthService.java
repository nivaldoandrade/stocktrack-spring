package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.AuthProviderGateway;
import com.nasa.stocktrack.application.gateways.TokenGateway;
import com.nasa.stocktrack.domain.entities.User;

public class AuthService {

    private final AuthProviderGateway authProviderGateway;

    private final TokenGateway tokenGateway;

    public AuthService(
            AuthProviderGateway authProviderGateway,
            TokenGateway tokenGateway
    ) {
        this.authProviderGateway = authProviderGateway;
        this.tokenGateway = tokenGateway;
    }

    public String createToken(String username, String password) {

        User user = authProviderGateway.authenticate(username, password);

        String userId  = String.valueOf(user.getId());

        return tokenGateway.generateToken(userId);
    }
}
