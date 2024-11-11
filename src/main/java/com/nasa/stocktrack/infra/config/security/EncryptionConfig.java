package com.nasa.stocktrack.infra.config.security;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import com.nasa.stocktrack.infra.gateways.security.BCryptPasswordEncoderGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncryptionConfig {

    @Bean
    public EncryptionGateway encryptionGateway() {
        return new BCryptPasswordEncoderGateway();
    }

    @Bean public PasswordEncoder passwordEncoder(EncryptionGateway encryptionGateway) {
        if(encryptionGateway instanceof BCryptPasswordEncoderGateway) {
            return new BCryptPasswordEncoder();
        }

        throw new IllegalArgumentException("PasswordEncoder not found");
    }
}
