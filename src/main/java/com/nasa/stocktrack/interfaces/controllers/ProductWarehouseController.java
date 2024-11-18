package com.nasa.stocktrack.interfaces.controllers;


import com.nasa.stocktrack.application.usecases.productWarehouse.DeleteProductWarehouseUseCase;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product-warehouses")
@AllArgsConstructor
public class ProductWarehouseController {

    private final DeleteProductWarehouseUseCase deleteProductWarehouseUseCase;

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{productId}/{warehouseId}")
    public ResponseEntity<Void> delete(
            @ValidUUID @PathVariable String productId,
            @ValidUUID @PathVariable String warehouseId
    ) {

        deleteProductWarehouseUseCase.execute(
                UUID.fromString(productId),
                UUID.fromString(warehouseId)
        );

        return ResponseEntity.noContent().build();
    }
}
