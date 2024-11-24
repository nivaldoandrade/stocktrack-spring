package com.nasa.stocktrack.interfaces.openapi.controllers;

import com.nasa.stocktrack.infra.constraints.ValidUUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Product Warehouse", description = "Product Warehouse management API")
public interface ProductWarehouseControllerOpenAPI {

    @Operation(
            summary = "Delete ProductWarehouse by product id and warehouse id",
            description = "Delete ProductWarehouse by product id and warehouse id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<Void> delete(
            @ValidUUID @PathVariable String productId,
            @ValidUUID @PathVariable String warehouseId
    );
}
