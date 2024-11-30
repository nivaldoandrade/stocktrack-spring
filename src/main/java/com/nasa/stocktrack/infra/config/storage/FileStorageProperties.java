package com.nasa.stocktrack.infra.config.storage;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "storage")
public class FileStorageProperties {

    private StorageType type;

    private Local local = new Local();

    private S3 s3 = new S3();

    public enum StorageType {
        LOCAL, S3
    }

    @Getter
    @Setter
    public class Local {
        private String uploadDir;
    }

    @Getter
    @Setter
    public class S3 {
        private String bucketName;
        private String region;
    }

    @PostConstruct
    private void init() {
        if (type == null) {
            throw new IllegalArgumentException(
                    "storage.type must not be null and must be one of: "
                    + Arrays.toString(StorageType.values())
                    + ". Check application.properties!"
            );
        }

        switch (type) {
            case LOCAL -> createLocalDir();
            case S3 -> checkEnvS3();
        }
    }

    private void createLocalDir() {
        //Implement logic local folder creation
    }

    private void checkEnvS3() {
        //Implement logic check env S3
    }
}
