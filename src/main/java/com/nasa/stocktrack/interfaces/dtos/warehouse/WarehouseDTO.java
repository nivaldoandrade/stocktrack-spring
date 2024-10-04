package com.nasa.stocktrack.interfaces.dtos.warehouse;

import com.nasa.stocktrack.domain.entities.Warehouse;
import lombok.Builder;

import java.util.UUID;

@Builder
public record WarehouseDTO(
        UUID id,

        String name
) {

    public static WarehouseDTO toResponse(Warehouse warehouse) {
        return WarehouseDTO.builder()
                .id(warehouse.getId())
                .name(warehouse.getName())
                .build();
    }
}
