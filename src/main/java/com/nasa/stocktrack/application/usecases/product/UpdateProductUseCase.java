package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.services.ProductService;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UpdateProductUseCase {

    private final ProductService productService;


    public UpdateProductUseCase(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    public void execute(Product product, FileData fileData) {
        Product productExisting = productService.getByProductIdWithoutWarehouses(product.getId());

        if(productExisting == null) {
            throw new ProductNotFoundException();
        }

        boolean hasSameCategoryId = product.getCategory().getId().equals(productExisting.getCategory().getId());

        if(!hasSameCategoryId) {
            Category newCategory = productService.getCategoryById(product.getCategory().getId());

            product.setCategory(newCategory);
        }

        if(!product.getName().equals(productExisting.getName())) {
            productService.validateNameUniqueness(product.getName());

        }

        if(!product.getCode().equals(productExisting.getCode())) {
            productService.validateCodeUniqueness(product.getCode());

        }

        String fileName = fileData != null
                ? productService.generateImageName(fileData.getFilename())
                : productExisting.getImage();

        product.setImage(fileName);

        productService.update(product);

        if(fileData != null) {
            productService.saveProductImage(fileData.getContent(), fileName);

            if(productExisting.getImage() != null) {
                productService.deleteProductImage(productExisting.getImage());
            }
        }
    }
}
