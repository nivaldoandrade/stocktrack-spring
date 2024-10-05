package com.nasa.stocktrack.interfaces.dtos.warehouse;

import com.nasa.stocktrack.domain.entities.Warehouse;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateWarehouseRequestDTO(

        @NotBlank(message = "Name is required")
        String name
) {

    public static Warehouse toDomain(UUID id, UpdateWarehouseRequestDTO updateWarehouseRequestDTO) {

        return new Warehouse(
                id,
                updateWarehouseRequestDTO.name()
        );
    }
}
