package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.warehouse.CreateWarehouseUseCase;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.warehouse.CreateWarehouseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.WarehouseDTO;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/warehouses")
@AllArgsConstructor
public class WarehouseController {

    private final CreateWarehouseUseCase createWarehouseUseCase;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Validated CreateWarehouseDTO createWarehouseDTO) {

        Warehouse warehouse = createWarehouseUseCase.execute(CreateWarehouseDTO.toDomain(createWarehouseDTO));

        URI uri = ResourceURIHelper.getURI(warehouse.getId());

        return ResponseEntity.created(uri).body(WarehouseDTO.toResponse(warehouse));
    }
}
