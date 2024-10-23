package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.application.services.RoleService;
import com.nasa.stocktrack.application.usecases.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDependencyInjection {

    @Bean
    CreateUserUseCase createUserUseCase(
            UserGateway userGateway,
            EncryptionGateway encryptionGateway,
            RoleService roleService) {

        return new CreateUserUseCase(userGateway, encryptionGateway, roleService);
    }
    @Bean
    ShowUserUseCase showUserUseCase(UserGateway userGateway) {
        return new ShowUserUseCase(userGateway);
    }

    @Bean
    ListUserUseCase listUserUseCase(UserGateway userGateway) {
        return new ListUserUseCase(userGateway);
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(
            UserGateway userGateway,
            EncryptionGateway encryptionGateway,
            ShowUserUseCase showUserUseCase,
            RoleService roleService
    ) {
        return new UpdateUserUseCase(userGateway, encryptionGateway, showUserUseCase, roleService);
    }

    @Bean
    DeleteUserUseCase deleteUserUseCase(UserGateway userGateway, ShowUserUseCase showUserUseCase) {
        return new DeleteUserUseCase(userGateway, showUserUseCase);
    }
}
