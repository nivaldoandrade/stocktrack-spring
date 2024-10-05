package com.nasa.stocktrack.interfaces.dtos.warehouse;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import lombok.Builder;

import java.util.List;

@Builder
public record ListWarehouseResponseDTO(
        List<WarehouseDTO> warehouses,

        Long totalItems,

        Integer totalPages
) {

    public static ListWarehouseResponseDTO toListResponseDTO(PaginatedList<Warehouse> warehousePaginatedList) {
        List<WarehouseDTO> warehouses = warehousePaginatedList.getItems().stream().map(WarehouseDTO::toResponse).toList();

        return ListWarehouseResponseDTO.builder()
                .warehouses(warehouses)
                .totalItems(warehousePaginatedList.getTotalItems())
                .totalPages(warehousePaginatedList.getTotalPages())
                .build();
    }
}
