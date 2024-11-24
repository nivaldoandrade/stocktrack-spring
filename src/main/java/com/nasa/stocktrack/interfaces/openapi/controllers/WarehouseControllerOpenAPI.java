package com.nasa.stocktrack.interfaces.openapi.controllers;

import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.infra.exceptions.RestErrorResponseWithErrors;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.CreateWarehouseDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.UpdateWarehouseRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.warehouse.WarehouseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Tag(name = "Warehouse", description = "Warehouse management API")
public interface WarehouseControllerOpenAPI {

    @Operation(
            summary = "Retrieve all Warehouses",
            description = "Get a Warehouses array",
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Page number for pagination. Min value: 0",
                            example = "0"
                    ),
                    @Parameter(
                            name = "size",
                            description = "Number of items per page. Min value: 1 - Max value: 10",
                            example = "10"
                    ),
                    @Parameter(
                            name = "orderBy",
                            description = "Sort by ascendant or descendant",
                            schema = @Schema(
                                    types = "String",
                                    allowableValues = {"ASC", "DESC"},
                                    defaultValue = "ASC"
                            )
                    ),
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content)
    })
    ResponseEntity<ListResponseDTO<WarehouseDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    );

    @Operation(
            summary = "Retrieve a Warehouse by id",
            description = "Get a Warehouse by id. The response is object of the WarehouseDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<WarehouseDTO> show(@Schema(allOf = {UUID.class}) @ValidUUID @PathVariable String id);

    @Operation(
            summary = "Create a Warehouse",
            description = "Create a new Warehouse by passing in a JSON representation of the CreateWarehouseDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = RestErrorResponseWithErrors.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )}),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<WarehouseDTO> create(@RequestBody @Validated CreateWarehouseDTO createWarehouseDTO);

    @Operation(
            summary = "Update a Warehouse by id",
            description = "Update a Warehouse by id. passing in a JSON representation of the UpdateWarehouseRequestDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = RestErrorResponseWithErrors.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )}),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
    })
    ResponseEntity<Void> update(
            @Schema(allOf = {UUID.class}) @ValidUUID @PathVariable String id,
            @RequestBody @Validated UpdateWarehouseRequestDTO updateWarehouseRequestDTO
    );

    @Operation(
            summary = "Delete a Warehouse by id",
            description = "Delete a Warehouse by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<Void> delete(@Schema(allOf = {UUID.class}) @ValidUUID @PathVariable String id);
}
