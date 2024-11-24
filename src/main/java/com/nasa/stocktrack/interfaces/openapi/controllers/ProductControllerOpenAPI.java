package com.nasa.stocktrack.interfaces.openapi.controllers;

import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.infra.exceptions.RestErrorResponseWithErrors;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.product.CreateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.product.ProductDTO;
import com.nasa.stocktrack.interfaces.dtos.product.UpdateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.productWarehouse.ListCreateOrUpdateProductWarehouseRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Tag(name = "Product", description = "Product management API")
public interface ProductControllerOpenAPI {

    @Operation(
            summary = "Retrieve all Products",
            description = "Get a Products array",
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
    ResponseEntity<ListResponseDTO<ProductDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    );

    @Operation
            (summary = "Retrieve an Image Product",
                    description = "Get an image product by name in JPEG and PNG format"
            )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
    })
    ResponseEntity<Resource> getImage(@PathVariable String imageName);

    @Operation(
            summary = "Retrieve a Product by id",
            description = "Get a Product by id. The response is object of the ProductDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<ProductDTO> show(@ValidUUID @PathVariable String id);

    @Operation(
            summary = "Create a Product",
            description = "Create a new Product by using a multipart/form-data request. " +
                    "The request should contain a representation of the CreateProductRequestDTO schema type. " +
                    "If a image is attached, it should be in one of the following formats: \".jpg\", \".jpeg\", \".png\" ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            )
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
    ResponseEntity<ProductDTO> create(
            @ModelAttribute @Validated CreateProductRequestDTO createProductRequestDTO
    ) throws IOException;

    @Operation(
            summary = "Update a Product by id",
            description = "Update a Product by id using a multipart/form-data request. " +
                    "The request  should contain a representation of the UpdateProductRequestDTO schema type. " +
                    "If a image is attached, it should be in one of the following formats: \".jpg\", \".jpeg\", \".png\" ",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE
                    )
            )
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
            @ValidUUID @PathVariable String id,
            @ModelAttribute @Validated UpdateProductRequestDTO updateProductRequestDTO
    ) throws IOException;

    @Operation(
            summary = "Create or Update ProductWarehouse by id",
            description = "Create or Update ProductWarehouse by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<Void> createOrUpdateProductWarehouse(
            @ValidUUID @PathVariable String productId,
            @RequestBody @Validated ListCreateOrUpdateProductWarehouseRequestDTO request
    );

    @Operation(
            summary = "Delete a Product by id",
            description = "Delete a Product by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<Void> delete(@ValidUUID @PathVariable String id);
}
