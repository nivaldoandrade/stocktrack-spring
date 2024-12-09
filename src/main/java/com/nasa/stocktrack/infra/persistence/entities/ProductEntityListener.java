package com.nasa.stocktrack.infra.persistence.entities;

import com.nasa.stocktrack.infra.config.storage.FileStorageProperties;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;


public class ProductEntityListener {
    private final FileStorageProperties fileStorageProperties;

    @Autowired(required = false)
    private S3Client s3Client;

    public ProductEntityListener(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = fileStorageProperties;
    }

    @PostLoad
    @PrePersist
    void setImageUrl(ProductEntity productEntity) {
        if(productEntity.getImage() == null) {
            return;
        }

        String imageName = productEntity.getImage();

        String imageUrl = switch (fileStorageProperties.getType()) {
            case S3 -> getUrlS3(imageName);
            case LOCAL -> getUrlLocal(imageName);
        };
        productEntity.setImageUrl(imageUrl);
    }

    private String getUrlLocal(String image) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("products/images/")
                .path(image)
                .toUriString();
    }

    private String getUrlS3(String image) {
        String bucketName = fileStorageProperties.getS3().getBucketName();

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(image)
                .build();


        return s3Client.utilities().getUrl(getUrlRequest).toString();
    }
}
