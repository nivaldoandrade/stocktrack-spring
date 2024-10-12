package com.nasa.stocktrack.interfaces.dtos.productWarehouse;

import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.interfaces.dtos.warehouse.WarehouseDTO;
import lombok.Builder;

@Builder
public record ProductWarehouseDTO(
        WarehouseDTO warehouse,

        Integer quantity,
        String location
) {

    public static ProductWarehouseDTO toResponse(ProductWarehouse productWarehouse) {
        WarehouseDTO warehouseDTO = WarehouseDTO.toResponse(productWarehouse.getWarehouse());

        return ProductWarehouseDTO.builder()
                .warehouse(warehouseDTO)
                .quantity(productWarehouse.getQuantity())
                .location(productWarehouse.getLocation())
                .build();
    }
}
