package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.product.*;
import com.nasa.stocktrack.application.usecases.productWarehouse.CreateOrUpdateProductWarehouseUseCase;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.product.CreateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.product.ListProductResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.product.ProductDTO;
import com.nasa.stocktrack.interfaces.dtos.product.UpdateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.productWarehouse.ListCreateOrUpdateProductWarehouseRequestDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ShowProductUseCase showProductUseCase;
    private final ListProductUseCase listProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final CreateOrUpdateProductWarehouseUseCase createOrUpdateProductWarehouseUseCase;

    @GetMapping
    public ResponseEntity<ListProductResponseDTO> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {

        PaginatedList<Product> productPaginatedList = listProductUseCase.execute(page, size, orderBy, search);

        return ResponseEntity.ok(ListProductResponseDTO.toResponse(productPaginatedList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> show(@ValidUUID @PathVariable String id) {
        Product product = showProductUseCase.execute(UUID.fromString(id));

        return ResponseEntity.ok(ProductDTO.toResponse(product));
    }
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Validated CreateProductRequestDTO createProductRequestDTO) {
        Product product = createProductUseCase.execute(CreateProductRequestDTO.toDomain(createProductRequestDTO));

        URI uri = ResourceURIHelper.getURI(product.getId());

        return ResponseEntity.created(uri).body(ProductDTO.toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @RequestBody @Validated UpdateProductRequestDTO updateProductRequestDTO
    ) {

        updateProductUseCase.execute(UpdateProductRequestDTO.toDomain(
                UUID.fromString(id),
                updateProductRequestDTO
        ));

        return ResponseEntity.noContent().build();
    }


    @PatchMapping("/{productId}/warehouses")
    public ResponseEntity<Void> createOrUpdateProductWarehouse(
            @ValidUUID @PathVariable String productId,
            @RequestBody @Validated ListCreateOrUpdateProductWarehouseRequestDTO request
    ) {
        List<ProductWarehouse> warehouses = ListCreateOrUpdateProductWarehouseRequestDTO.toDomain(
                request
        );

        createOrUpdateProductWarehouseUseCase.execute(UUID.fromString(productId), warehouses);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@ValidUUID @PathVariable String id) {
        deleteProductUseCase.execute(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }
}
