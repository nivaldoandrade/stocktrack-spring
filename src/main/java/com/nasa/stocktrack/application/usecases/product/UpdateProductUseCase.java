package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.services.ProductService;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductNotFoundException;

public class UpdateProductUseCase {

    private final ProductGateway productGateway;

    private final ProductService productService;

    private final ShowCategoryUseCase showCategoryUseCase;

    public UpdateProductUseCase(
            ProductGateway productGateway,
            ProductService productService,
            ShowCategoryUseCase showCategoryUseCase
    ) {
        this.productGateway = productGateway;
        this.productService = productService;
        this.showCategoryUseCase = showCategoryUseCase;
    }

    public void execute(Product product) {
        Product productExisting = productGateway.findById(product.getId());

        if(productExisting == null) {
            throw new ProductNotFoundException();
        }

        if(!product.getCategory().equals(productExisting.getCategory())) {
            Category newCategory = showCategoryUseCase.execute(product.getCategory().getId());

            productExisting.setCategory(newCategory);
        }

        if(!product.getName().equals(productExisting.getName())) {
            productService.validateNameUniqueness(product.getName());

            productExisting.setName(product.getName());
        }

        if(!product.getCode().equals(productExisting.getCode())) {
            productService.validateCodeUniqueness(product.getCode());

            productExisting.setCode(product.getCode());
        }

        productExisting.setBrand(product.getBrand());

        productGateway.update(productExisting);
    }
}
