package com.nasa.stocktrack.interfaces.dtos;

import com.nasa.stocktrack.domain.entities.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateCategoryRequestDTO(

        @NotBlank(message = "Name is required")
        String name
) {

    public static Category toDomain(UUID id, UpdateCategoryRequestDTO updateCategoryRequestDTO) {
        return new Category(
                id,
                updateCategoryRequestDTO.name()
        );
    }
}