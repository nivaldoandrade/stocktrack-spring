package com.nasa.stocktrack.application.usecases.category;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.exceptions.CategoryExistsException;

import java.util.UUID;

public class UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    private final ShowCategoryUseCase showCategoryUseCase;

    public UpdateCategoryUseCase(CategoryGateway categoryGateway, ShowCategoryUseCase showCategoryUseCase) {
        this.categoryGateway = categoryGateway;
        this.showCategoryUseCase = showCategoryUseCase;
    }

    public void execute(Category category) {
        Category categoryExisting = showCategoryUseCase.execute(category.getId());

        if(categoryExisting.getName().equals(category.getName())) {
            return;
        }

        Category categoryNameExists = categoryGateway.findByName(category.getName());

        if(categoryNameExists != null && ! categoryNameExists.equals(categoryExisting)) {
            throw new CategoryExistsException();
        }

        categoryExisting.setName(category.getName());

        categoryGateway.update(categoryExisting);
    }
}
