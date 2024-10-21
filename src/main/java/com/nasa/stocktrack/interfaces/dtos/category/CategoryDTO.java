package com.nasa.stocktrack.interfaces.dtos.category;

import com.nasa.stocktrack.domain.entities.Category;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryDTO(
        UUID id,

        String name
) {

    public static CategoryDTO toResponse(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
