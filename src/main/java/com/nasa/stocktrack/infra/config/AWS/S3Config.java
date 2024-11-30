package com.nasa.stocktrack.infra.config.AWS;

import com.nasa.stocktrack.infra.config.storage.FileStorageProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Getter
@Setter
@Configuration
@ConditionalOnProperty(name = "storage.type", havingValue = "S3")
public class S3Config {

    private String region;

    private final StaticCredentialsProvider credentialsProvider;

    public S3Config(StaticCredentialsProvider credentialsProvider, FileStorageProperties fileStorageProperties) {
        this.region = fileStorageProperties.getS3().getRegion();
        this.credentialsProvider = credentialsProvider;
    }

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
