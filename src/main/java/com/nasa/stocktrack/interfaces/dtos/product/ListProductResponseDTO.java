package com.nasa.stocktrack.interfaces.dtos.product;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Product;
import lombok.Builder;

import java.util.List;

@Builder
public record ListProductResponseDTO(
        List<ProductDTO> products,

        Long totalItems,

        Integer totalPages
) {

    public static ListProductResponseDTO toResponse(PaginatedList<Product> productPaginatedList) {
        List<ProductDTO> productDTOS = productPaginatedList.getItems().stream().map(ProductDTO::toResponse).toList();

        return ListProductResponseDTO.builder()
                .products(productDTOS)
                .totalItems(productPaginatedList.getTotalItems())
                .totalPages(productPaginatedList.getTotalPages())
                .build();
    }
}
