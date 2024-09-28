package com.nasa.stocktrack.application.usecases.category;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.exceptions.CategoryExistsException;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public Category execute(Category category) {
        Category categoryExisting = categoryGateway.findByName(category.getName());

        if(categoryExisting != null) {
            throw new CategoryExistsException();
        }

        return categoryGateway.create(category);
    }
}
