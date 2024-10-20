package com.nasa.stocktrack.infra.gateways.security;

import com.nasa.stocktrack.application.gateways.EncryptionGateway;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderGateway implements EncryptionGateway {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String generateHash(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
