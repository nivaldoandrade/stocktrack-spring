package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.exceptions.UserInUseException;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User execute(User user) {

        User usernameAlreadyInUse = userGateway.findByUsername(user.getUsername());

        if(usernameAlreadyInUse != null) {
            throw new UserInUseException();
        }

        return userGateway.create(user);
    }
}
