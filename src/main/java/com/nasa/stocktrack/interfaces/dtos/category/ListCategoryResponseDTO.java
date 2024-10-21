package com.nasa.stocktrack.interfaces.dtos.category;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Category;
import lombok.Builder;

import java.util.List;

@Builder
public record ListCategoryResponseDTO(
        List<CategoryDTO> categories,

        Long totalItems,

        Integer totalPages
) {

    public static ListCategoryResponseDTO toResponse(PaginatedList<Category> categoryPaginatedList) {
        List<CategoryDTO> categories = categoryPaginatedList.getItems().stream().map(CategoryDTO::toResponse).toList();

        return ListCategoryResponseDTO.builder()
                .categories(categories)
                .totalItems(categoryPaginatedList.getTotalItems())
                .totalPages(categoryPaginatedList.getTotalPages())
                .build();
    }
}
