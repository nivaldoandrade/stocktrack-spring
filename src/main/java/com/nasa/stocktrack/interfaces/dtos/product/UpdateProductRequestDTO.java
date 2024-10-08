package com.nasa.stocktrack.interfaces.dtos.product;

import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        String category_id
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
