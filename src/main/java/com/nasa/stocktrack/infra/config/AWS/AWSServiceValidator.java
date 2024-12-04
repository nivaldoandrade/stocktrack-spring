package com.nasa.stocktrack.infra.config.AWS;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class AWSServiceValidator {

    @Value("${aws.enabled}")
    private boolean awsEnabled;

    @Value("${storage.type}")
    private String storageType;

    @PostConstruct
    private void init() {
        boolean s3Enabled = "s3".equalsIgnoreCase(storageType);

        Map<String, Boolean> services = Map.of(
                "S3", s3Enabled
        );

        services.forEach((service, enabled) -> {
            if(enabled && !awsEnabled) {
                throw new IllegalStateException(
                        String.format(
                                "AWS %s is enabled, but global AWS configuration is disabled (aws.enabled=false). " +
                                "Please set 'aws.enabled=true' to use AWS services.",
                                service
                        )
                );
            }
        });
    }
}
