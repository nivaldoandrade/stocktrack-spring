package com.nasa.stocktrack.infra.config.AWS;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aws.credentials")
@ConditionalOnProperty(name = "aws.enabled", havingValue = "true")
public class AWSConfig {
    private String accessKey;

    private String secretKey;

    public AWSConfig(AWSProperties awsProperties) {
        this.accessKey = awsProperties.getAccessKey();
        this.secretKey = awsProperties.getAccessKey();
    }

    @Bean
    public StaticCredentialsProvider awsCredentials() {
        AwsBasicCredentials awsBasicCredentials =  AwsBasicCredentials.create(accessKey, secretKey);

        return StaticCredentialsProvider.create(awsBasicCredentials);
    }

}
