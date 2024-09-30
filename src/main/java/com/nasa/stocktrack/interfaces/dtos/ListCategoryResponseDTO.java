package com.nasa.stocktrack.interfaces.dtos;

import com.nasa.stocktrack.domain.entities.ListCategory;
import lombok.Builder;

import java.util.List;

@Builder
public record ListCategoryResponseDTO(
        List<CategoryDTO> categories,

        Long totalItems,

        Integer totalPages
) {

    public static ListCategoryResponseDTO toResponse(ListCategory listCategory) {
        List<CategoryDTO> categories = listCategory.getCategories().stream().map(CategoryDTO::toResponse).toList();

        return ListCategoryResponseDTO.builder()
                .categories(categories)
                .totalItems(listCategory.getTotalItems())
                .totalPages(listCategory.getTotalPages())
                .build();
    }
}
