package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.exceptions.SelfDeletionException;

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

    public void execute(UUID userDeleteId, UUID userAuthId) {
        if(userAuthId.equals(userDeleteId)) {
            throw new SelfDeletionException();
        }

        User user  = showUserUseCase.execute(userDeleteId);

        userGateway.delete(user);
    }
}
