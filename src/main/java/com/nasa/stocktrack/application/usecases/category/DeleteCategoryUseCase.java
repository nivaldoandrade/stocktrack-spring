package com.nasa.stocktrack.application.usecases.category;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.exceptions.CategoryNotFoundException;

import java.util.UUID;

public class DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DeleteCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void execute(UUID id) {
        Category category = categoryGateway.findById(id);

        if(category == null) {
            throw new CategoryNotFoundException();
        }

        categoryGateway.delete(category);
    }

}
