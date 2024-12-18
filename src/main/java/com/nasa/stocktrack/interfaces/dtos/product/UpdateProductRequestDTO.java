package com.nasa.stocktrack.interfaces.dtos.product;

import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.infra.constraints.FileTypePattern;
import com.nasa.stocktrack.infra.constraints.ValidFileMaxSizePattern;
import com.nasa.stocktrack.infra.constraints.ValidFileMimeTypePattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record UpdateProductRequestDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Code is required")
        String code,

        @NotBlank(message = "Brand is required")
        String brand,

        @ValidUUID
        @NotNull(message = "Category id is required")
        String category_id,

        @ValidFileMaxSizePattern(maxSizeInMB = 5)
        @FileTypePattern(allowedExtensions = {".jpg", ".jpeg", ".png"})
        @ValidFileMimeTypePattern(mimeTypes = {"image/jpeg", "image/png"})
        MultipartFile image
) {

    public static Product toDomain(UUID id, UpdateProductRequestDTO updateProductRequestDTO) {

        return new Product(
                id,
                updateProductRequestDTO.name,
                updateProductRequestDTO.code,
                updateProductRequestDTO.brand,
                new Category(UUID.fromString(updateProductRequestDTO.category_id))
        );
    }
}
