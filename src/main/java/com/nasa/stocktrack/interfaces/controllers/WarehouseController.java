package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.warehouse.CreateWarehouseUseCase;
import com.nasa.stocktrack.application.usecases.warehouse.ListWarehouseUseCase;
import com.nasa.stocktrack.application.usecases.warehouse.ShowWarehouseUseCase;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.warehouse.CreateWarehouseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.ListWarehouseResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.WarehouseDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/warehouses")
@AllArgsConstructor
public class WarehouseController {

    private final CreateWarehouseUseCase createWarehouseUseCase;
    private final ShowWarehouseUseCase showWarehouseUseCase;
    private final ListWarehouseUseCase listWarehouseUseCase;

    @GetMapping
    public ResponseEntity<ListWarehouseResponseDTO> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {
        PaginatedList<Warehouse> warehousePaginatedList = listWarehouseUseCase.execute(
                page,
                size,
                OrderByEnum.fromString(orderBy),
                search
        );

        return ResponseEntity.ok(ListWarehouseResponseDTO.toListResponseDTO(warehousePaginatedList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseDTO> show(@ValidUUID @PathVariable String id) {
        Warehouse warehouse = showWarehouseUseCase.execute(UUID.fromString(id));

        return ResponseEntity.ok(WarehouseDTO.toResponse(warehouse));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated CreateWarehouseDTO createWarehouseDTO) {

        Warehouse warehouse = createWarehouseUseCase.execute(CreateWarehouseDTO.toDomain(createWarehouseDTO));

        URI uri = ResourceURIHelper.getURI(warehouse.getId());

        return ResponseEntity.created(uri).body(WarehouseDTO.toResponse(warehouse));
    }
}
