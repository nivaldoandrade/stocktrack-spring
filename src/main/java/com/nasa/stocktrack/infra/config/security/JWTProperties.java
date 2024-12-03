package com.nasa.stocktrack.infra.config.security;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JWTProperties {
    private String secretKey;

    private long expiresIn;

    @PostConstruct
    private void init() {

        if(secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("JWT SecretKey cannot be null or blank");
        }

        Integer keyLength = secretKeyInBase64Length();

        if(keyLength == null) {
            throw new IllegalArgumentException("JWT SecretKey must be a valid Base64 encoded string");
        }

        if(keyLength < 32) {
            throw new IllegalArgumentException("JWT SecretKey must be at least 256 bits (32 bytes) for HS256 algorithm");
        }

        if(expiresIn <= 0) {
            throw new IllegalArgumentException("JWT ExpiresIn must be greater than zero");
        }
    }

    private Integer secretKeyInBase64Length() {
        try {
            return Base64.getDecoder().decode(secretKey).length;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
