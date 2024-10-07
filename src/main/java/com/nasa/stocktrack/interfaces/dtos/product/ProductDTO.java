package com.nasa.stocktrack.interfaces.dtos.product;

import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.interfaces.dtos.CategoryDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProductDTO(
        UUID id,

        String name,

        String code,

        String brand,

        CategoryDTO category
) {

    public static ProductDTO toResponse(Product product) {
        CategoryDTO categoryDTO = CategoryDTO.toResponse(product.getCategory());

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .brand(product.getBrand())
                .category(categoryDTO)
                .build();
    }
}
