package com.nasa.stocktrack.interfaces.dtos;

import com.nasa.stocktrack.domain.entities.FileData;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public record FileDataDTO(
        InputStream content,

        String filename
) {

    public static FileData toDomain(MultipartFile file) throws IOException {
        return file == null
                ? null
                : new FileData(file.getOriginalFilename(), file.getInputStream());
    }
}
