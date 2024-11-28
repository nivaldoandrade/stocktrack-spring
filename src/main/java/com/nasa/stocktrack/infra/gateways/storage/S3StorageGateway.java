package com.nasa.stocktrack.infra.gateways.storage;

import com.nasa.stocktrack.application.gateways.FileStorageGateway;
import com.nasa.stocktrack.infra.exceptions.FileStorageException;
import com.nasa.stocktrack.infra.utis.TikaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.*;

@Component
public class S3StorageGateway implements FileStorageGateway {

    @Value("${storage.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client;

    public S3StorageGateway(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public InputStream getFile(String filename) {
        return null;
    }

    @Override
    public void saveFile(InputStream content, String filename) {
        try {
            byte[] fileBytes = content.readAllBytes();

            String mineType = TikaUtils.getMimeType(fileBytes);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filename)
                    .contentType(mineType)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));
        } catch (Exception e) {
            throw new FileStorageException("Error storing file "
                    + filename
                    + ", please try again"
            );
        }
    }

    @Override
    public void deleteFile(String filename) {

    }
}
