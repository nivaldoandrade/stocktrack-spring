package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.exceptions.UserInUseException;

public class UpdateUserUseCase {

    private final UserGateway userGateway;

    private final EncryptionGateway encryptionGateway;

    private final ShowUserUseCase showUserUseCase;

    public UpdateUserUseCase(
            UserGateway userGateway,
            EncryptionGateway encryptionGateway,
            ShowUserUseCase showUserUseCase
    ) {
        this.userGateway = userGateway;
        this.encryptionGateway = encryptionGateway;
        this.showUserUseCase = showUserUseCase;
    }

    public void execute(User user) {
        User userExisting = showUserUseCase.execute(user.getId());

        if(!user.getUsername().equals(userExisting.getUsername())) {
            User userUsernameExists = userGateway.findByUsername(user.getUsername());

            if(userUsernameExists != null) {
                throw new UserInUseException();
            }
        }

        String passwordEncode = user.getPassword() == null
                ? userExisting.getPassword()
                : encryptionGateway.generateHash(user.getPassword());

        user.setPassword(passwordEncode);

        userGateway.update(user);
    }
}
