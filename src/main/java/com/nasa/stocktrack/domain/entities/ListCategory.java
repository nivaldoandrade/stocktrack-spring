package com.nasa.stocktrack.domain.entities;

import java.util.List;

public class ListCategory {

    private final List<Category> categories;

    private final Long totalItems;

    private final Integer totalPages;

    public ListCategory(List<Category> categories, Long totalItems, Integer totalPages) {
        this.categories = categories;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Long getTotalItems() {
        return totalItems;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
