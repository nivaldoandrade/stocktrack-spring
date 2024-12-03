package com.nasa.stocktrack.application.usecases.product;


import com.nasa.stocktrack.application.services.ProductService;

import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public class CreateProductUseCase {
    private final ProductService productService;

    private final ProductWarehouseService productWarehouseService;

    public CreateProductUseCase(
            ProductService productService,
            ProductWarehouseService productWarehouseService
    ) {
        this.productService = productService;
        this.productWarehouseService = productWarehouseService;
    }

    @Transactional
    public Product execute(Product product, FileData fileData) {
        productService.validateNameUniqueness(product.getName());
        productService.validateCodeUniqueness(product.getCode());

        String fileName = fileData != null
                ? productService.generateImageName(fileData.getFilename())
                : null;

        product.setImage(fileName);

        Category category = productService.getCategoryById(product.getCategory().getId());

        product.setCategory(category);

        int total = product.getProductWarehouses()
                .stream().mapToInt(ProductWarehouse::getQuantity).sum();

        product.setTotal(total);

        Product newProduct =  productService.create(product);

        List<ProductWarehouse> productWarehouses = productWarehouseService.mapToProductWarehouses(
                product.getProductWarehouses(),
                newProduct
        );

        productWarehouses =  productWarehouseService.saveAll(productWarehouses);

        newProduct.setProductWarehouses(productWarehouses);

        if(fileData != null) {
            productService.saveProductImage(fileData.getContent(), newProduct.getImage());
        }

        return newProduct;
    }
}
