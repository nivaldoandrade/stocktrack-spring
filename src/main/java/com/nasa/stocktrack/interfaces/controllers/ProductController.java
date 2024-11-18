package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.product.*;
import com.nasa.stocktrack.application.usecases.productWarehouse.CreateOrUpdateProductWarehouseUseCase;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.FileDataDTO;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.product.CreateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.product.ProductDTO;
import com.nasa.stocktrack.interfaces.dtos.product.UpdateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.productWarehouse.ListCreateOrUpdateProductWarehouseRequestDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
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
    private final GetImageProductUseCase getImageProductUseCase;

    @GetMapping
    public ResponseEntity<ListResponseDTO<ProductDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {

        PaginatedList<Product> productPaginatedList = listProductUseCase.execute(page, size, orderBy, search);

        return ResponseEntity.ok(ListResponseDTO.toResponse(productPaginatedList, ProductDTO::toResponse));
    }

    @GetMapping("/images/{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        InputStream imageStream = getImageProductUseCase.execute(imageName);

        MediaType contentType = imageName.endsWith(".png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(new InputStreamResource(imageStream));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> show(@ValidUUID @PathVariable String id) {
        Product product = showProductUseCase.execute(UUID.fromString(id));

        return ResponseEntity.ok(ProductDTO.toResponse(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDTO> create(
            @ModelAttribute @Validated CreateProductRequestDTO createProductRequestDTO
    ) throws IOException {
        FileData fileData = FileDataDTO.toDomain(createProductRequestDTO.image());

        Product product = createProductUseCase.execute(
                CreateProductRequestDTO.toDomain(createProductRequestDTO),
                fileData
        );

        URI uri = ResourceURIHelper.getURI(product.getId());

        return ResponseEntity.created(uri).body(ProductDTO.toResponse(product));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @ModelAttribute @Validated UpdateProductRequestDTO updateProductRequestDTO
    ) throws IOException {

        FileData fileData = FileDataDTO.toDomain(updateProductRequestDTO.image());

        updateProductUseCase.execute(
                UpdateProductRequestDTO.toDomain(UUID.fromString(id), updateProductRequestDTO),
                fileData
        );

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@ValidUUID @PathVariable String id) {
        deleteProductUseCase.execute(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }
}
