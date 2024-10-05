package com.nasa.stocktrack.domain.dtos;

import java.util.List;

public class PaginatedList<T> {

    private final List<T> items;

    private final Long totalItems;

    private final Integer TotalPages;

    public PaginatedList(List<T> items, Long totalItems, Integer totalPages) {
        this.items = items;
        this.totalItems = totalItems;
        TotalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Integer getTotalPages() {
        return TotalPages;
    }
}
