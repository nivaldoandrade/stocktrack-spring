package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.warehouse.*;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.CreateWarehouseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.UpdateWarehouseRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.WarehouseDTO;
import com.nasa.stocktrack.interfaces.openapi.controllers.WarehouseControllerOpenAPI;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/warehouses")
@AllArgsConstructor
public class WarehouseController implements WarehouseControllerOpenAPI {

    private final CreateWarehouseUseCase createWarehouseUseCase;
    private final ShowWarehouseUseCase showWarehouseUseCase;
    private final ListWarehouseUseCase listWarehouseUseCase;
    private final UpdateWarehouseUseCase updateWarehouseUseCase;
    private final DeleteWarehouseUseCase deleteWarehouseUseCase;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponseDTO<WarehouseDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {
        PaginatedList<Warehouse> warehousePaginatedList = listWarehouseUseCase.execute(
                page,
                size,
                OrderByEnum.fromString(orderBy),
                search
        );

        return ResponseEntity.ok(ListResponseDTO.toResponse(warehousePaginatedList, WarehouseDTO::toResponse));
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WarehouseDTO> show(@ValidUUID @PathVariable String id) {
        Warehouse warehouse = showWarehouseUseCase.execute(UUID.fromString(id));

        return ResponseEntity.ok(WarehouseDTO.toResponse(warehouse));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WarehouseDTO> create(@RequestBody @Validated CreateWarehouseDTO createWarehouseDTO) {

        Warehouse warehouse = createWarehouseUseCase.execute(CreateWarehouseDTO.toDomain(createWarehouseDTO));

        URI uri = ResourceURIHelper.getURI(warehouse.getId());

        return ResponseEntity.created(uri).body(WarehouseDTO.toResponse(warehouse));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @RequestBody @Validated UpdateWarehouseRequestDTO updateWarehouseRequestDTO
    ) {
        Warehouse warehouse = UpdateWarehouseRequestDTO.toDomain(UUID.fromString(id), updateWarehouseRequestDTO);

        updateWarehouseUseCase.execute(warehouse);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@ValidUUID @PathVariable String id) {

        deleteWarehouseUseCase.execute(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }
}
