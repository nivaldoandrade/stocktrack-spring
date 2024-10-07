package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductExistsException;

public class CreateProductUseCase {

    private final ProductGateway productGateway;

    private final ShowCategoryUseCase showCategoryUseCase;

    public CreateProductUseCase(
            ProductGateway productGateway,
            ShowCategoryUseCase showCategoryUseCase
    ) {
        this.productGateway = productGateway;
        this.showCategoryUseCase = showCategoryUseCase;
    }

    public Product execute(Product product) {
        Product productByNameExisting = productGateway.findByName(product.getName());

        if(productByNameExisting != null) {
            throw new ProductExistsException("The product with this name already exists");
        }

        Product productByCodeExisting = productGateway.findByCode(product.getCode());

        if(productByCodeExisting != null) {
            throw new ProductExistsException("The product with code is already exists");
        }

        Category category = showCategoryUseCase.execute(product.getCategory().getId());

        product.setCategory(category);

        return productGateway.create(product);
    }
}
