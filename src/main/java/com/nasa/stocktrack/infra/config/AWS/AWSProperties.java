package com.nasa.stocktrack.infra.config.AWS;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.credentials")
@ConditionalOnProperty(name = "aws.enabled", havingValue = "true")
public class AWSProperties {
    private String accessKey;

    private String secretKey;

    @PostConstruct
    private void init() {
        if(accessKey == null || accessKey.isEmpty()) {
            throw new IllegalArgumentException("AWS AccessKey must not be null or empty. Check application.properties!");
        }

        if(secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("AWS SecretKey must not be null or empty. Check application.properties!");
        }
    }
}
