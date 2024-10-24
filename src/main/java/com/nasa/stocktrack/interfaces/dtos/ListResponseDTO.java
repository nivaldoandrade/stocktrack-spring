package com.nasa.stocktrack.interfaces.dtos;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import lombok.Builder;

import java.util.List;
import java.util.function.Function;

@Builder
public record ListResponseDTO<T>(
        List<T> items,

        Long totalItems,

        Integer totalPages
) {

    public static <T, E> ListResponseDTO<T> toResponse(PaginatedList<E> paginatedList, Function<E, T> mapper) {
        List<T> items = paginatedList.getItems().stream().map(mapper).toList();

        return ListResponseDTO.<T>builder()
                .items(items)
                .totalItems(paginatedList.getTotalItems())
                .totalPages(paginatedList.getTotalPages())
                .build();
    }
}
