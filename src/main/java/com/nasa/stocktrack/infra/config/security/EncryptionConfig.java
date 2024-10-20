package com.nasa.stocktrack.infra.config.security;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import com.nasa.stocktrack.infra.gateways.security.BCryptPasswordEncoderGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {

    @Bean
    public EncryptionGateway encryptionGateway() {
        return new BCryptPasswordEncoderGateway();
    }
}
