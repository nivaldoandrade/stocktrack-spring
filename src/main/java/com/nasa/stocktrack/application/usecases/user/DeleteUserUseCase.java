package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.entities.User;

import java.util.UUID;

public class DeleteUserUseCase {

    private final UserGateway userGateway;

    private final ShowUserUseCase showUserUseCase;

    public DeleteUserUseCase(
            UserGateway userGateway,
            ShowUserUseCase showUserUseCase
    ) {
        this.userGateway = userGateway;
        this.showUserUseCase = showUserUseCase;
    }

    public void execute(UUID id) {
        User user  = showUserUseCase.execute(id);


        userGateway.delete(user);
    }
}
