package com.nasa.stocktrack.interfaces.dtos.productWarehouse;

import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record CreateProductWarehouseRequestDTO(

        @ValidUUID
        @NotNull(message = "Warehouse id is required")
        String warehouseId,

        @NotNull(message = "Quantity is required")
        @PositiveOrZero(message = "Quantity must be positve or zero")
        Integer quantity,

        @NotBlank(message = "Location is required")
        String location
) {

        public static ProductWarehouse toDomain(CreateProductWarehouseRequestDTO createProductWarehouseRequestDTO) {
                return new ProductWarehouse(
                        null,
                        new Warehouse(UUID.fromString(createProductWarehouseRequestDTO.warehouseId)),
                        createProductWarehouseRequestDTO.quantity(),
                        createProductWarehouseRequestDTO.location()
                );
        }
}
