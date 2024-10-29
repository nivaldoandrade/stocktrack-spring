package com.nasa.stocktrack.application.usecases.product;


import com.nasa.stocktrack.application.services.ProductService;

import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;



public class CreateProductUseCase {
    private final ProductService productService;

    public CreateProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    public Product execute(Product product, FileData fileData) {
        Product newProduct = productService.create(product, fileData);

        if(fileData != null) {
            productService.saveProductImage(fileData.getContent(), newProduct.getImage());
        }

        return newProduct;
    }
}
