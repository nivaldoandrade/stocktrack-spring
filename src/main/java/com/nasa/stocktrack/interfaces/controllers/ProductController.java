package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.product.CreateProductUseCase;
import com.nasa.stocktrack.application.usecases.product.ShowProductUseCase;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.product.CreateProductRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.product.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ShowProductUseCase showProductUseCase;

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
}
