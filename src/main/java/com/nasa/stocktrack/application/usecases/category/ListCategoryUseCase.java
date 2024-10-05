package com.nasa.stocktrack.application.usecases.category;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

public class ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public PaginatedList<Category> execute(Integer page, Integer size, OrderByEnum orderBy, String search) {
        return categoryGateway.list(page, size, orderBy, search);
    }
}
