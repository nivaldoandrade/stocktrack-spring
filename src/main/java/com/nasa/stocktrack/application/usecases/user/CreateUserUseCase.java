package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.application.services.RoleService;
import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.exceptions.UserInUseException;

public class CreateUserUseCase {

    private final UserGateway userGateway;

    private final EncryptionGateway encryptionGateway;

    private final RoleService roleService;

    public CreateUserUseCase(
            UserGateway userGateway,
            EncryptionGateway encryptionGateway,
            RoleService roleService
    ) {
        this.userGateway = userGateway;
        this.encryptionGateway = encryptionGateway;
        this.roleService = roleService;
    }

    public User execute(User user) {

        User usernameAlreadyInUse = userGateway.findByUsername(user.getUsername());

        if(usernameAlreadyInUse != null) {
            throw new UserInUseException();
        }

        Role role = roleService.getRole(user.getRole().getName());

        user.setRole(role);

        String passwordEncode = encryptionGateway.generateHash(user.getPassword());

        user.setPassword(passwordEncode);

        return userGateway.create(user);
    }
}
