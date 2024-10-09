package com.nasa.stocktrack.application.usecases.category;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.CategoryInUseException;
import com.nasa.stocktrack.domain.exceptions.CategoryNotFoundException;

import java.util.UUID;

public class DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;
    private final ProductGateway productGateway;

    public DeleteCategoryUseCase(
            CategoryGateway categoryGateway,
            ProductGateway productGateway
    ) {
        this.categoryGateway = categoryGateway;
        this.productGateway = productGateway;
    }

    public void execute(UUID id) {
        Category category = categoryGateway.findById(id);

        if(category == null) {
            throw new CategoryNotFoundException();
        }

        Product productExisting = productGateway.findFirstByCategoryId(id);

        if(productExisting != null) {
            throw new CategoryInUseException();
        }

        categoryGateway.delete(category);
    }

}
