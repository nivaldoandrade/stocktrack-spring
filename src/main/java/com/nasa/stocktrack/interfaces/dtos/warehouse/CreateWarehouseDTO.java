package com.nasa.stocktrack.interfaces.dtos.warehouse;

import com.nasa.stocktrack.domain.entities.Warehouse;
import jakarta.validation.constraints.NotBlank;

public record CreateWarehouseDTO(

        @NotBlank(message = "Name is required")
        String name
) {

        public static Warehouse toDomain(CreateWarehouseDTO createWarehouseDTO) {
                return new Warehouse(
                        createWarehouseDTO.name()
                );
        }

}
