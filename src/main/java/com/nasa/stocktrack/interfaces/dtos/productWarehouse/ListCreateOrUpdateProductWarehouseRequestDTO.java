package com.nasa.stocktrack.interfaces.dtos.productWarehouse;

import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ListCreateOrUpdateProductWarehouseRequestDTO(


        @NotEmpty(message = "StockPerWarehouse is required")
        List<@Valid CreateProductWarehouseRequestDTO> stockPerWarehouse
) {

    public static List<ProductWarehouse> toDomain(ListCreateOrUpdateProductWarehouseRequestDTO requestDTO) {
        return  requestDTO
                .stockPerWarehouse()
                .stream()
                .map(CreateProductWarehouseRequestDTO::toDomain)
                .toList();
    }
}
