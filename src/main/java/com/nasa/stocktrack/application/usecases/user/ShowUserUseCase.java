package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.exceptions.UserNotFoundException;

import java.util.UUID;

public class ShowUserUseCase {

    private final UserGateway userGateway;

    public ShowUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(UUID id) {
        User user = userGateway.findById(id);

        if(user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
