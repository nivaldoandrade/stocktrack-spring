package com.nasa.stocktrack.application.usecases.category;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.domain.entities.ListCategory;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

public class ListCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public ListCategory execute(Integer page, Integer size, OrderByEnum orderBy, String search) {
        return categoryGateway.list(page, size, orderBy, search);
    }
}
