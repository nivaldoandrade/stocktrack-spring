package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.services.ProductService;


import java.util.UUID;

public class DeleteProductUseCase {

    private final ProductService productService;

    public DeleteProductUseCase( ProductService productService) {
        this.productService = productService;
    }

    public void execute(UUID id) {
        productService.delete(id);
    }
}
