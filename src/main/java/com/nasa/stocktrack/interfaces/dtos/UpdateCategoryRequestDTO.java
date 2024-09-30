package com.nasa.stocktrack.interfaces.dtos;

import com.nasa.stocktrack.domain.entities.Category;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

public record UpdateCategoryRequestDTO(

        @NotEmpty(message = "Name is required")
        String name
) {

    public static Category toDomain(UUID id, UpdateCategoryRequestDTO updateCategoryRequestDTO) {
        return new Category(
                id,
                updateCategoryRequestDTO.name()
        );
    }
}