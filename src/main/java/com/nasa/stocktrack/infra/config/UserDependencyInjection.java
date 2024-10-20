package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.application.usecases.user.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDependencyInjection {

    @Bean
    CreateUserUseCase createUserUseCase(UserGateway userGateway, EncryptionGateway encryptionGateway) {
        return new CreateUserUseCase(userGateway, encryptionGateway);
    }
}
