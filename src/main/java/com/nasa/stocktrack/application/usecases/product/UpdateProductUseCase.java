package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.services.ProductService;
import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;

public class UpdateProductUseCase {

    private final ProductService productService;

    public UpdateProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    public void execute(Product product, FileData fileData) {
        productService.update(product, fileData);
    }
}
