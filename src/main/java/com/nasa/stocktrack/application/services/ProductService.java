package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductExistsException;

import java.io.InputStream;
import java.util.UUID;

public class ProductService {

    private final ProductGateway productGateway;

    private final FileStorageService fileStorageService;

    private final ShowCategoryUseCase showCategoryUseCase;


    public ProductService(
            ProductGateway productGateway,
            FileStorageService fileStorageService,
            ShowCategoryUseCase showCategoryUseCase
    ) {
        this.productGateway = productGateway;
        this.fileStorageService = fileStorageService;
        this.showCategoryUseCase = showCategoryUseCase;
    }

    public String generateImageName(String filename) {
        return fileStorageService.generateFilename(filename);
    }

    public Category getCategoryById(UUID categoryId) {
        return showCategoryUseCase.execute(categoryId);
    }

    public Product getByProductIdWithoutWarehouses(UUID productId) {
        return productGateway.findByIdWithoutWarehouses(productId);
    }

    public Product create(Product product) {
        return productGateway.create(product);
    }

    public void update(Product product) {
        productGateway.update(product);
    }

    public void saveProductImage(InputStream content, String fileName) {
        fileStorageService.saveFile(content, fileName);
    }

    public void deleteProductImage(String fileName) {
        fileStorageService.deleteFile(fileName);
    }

    public void validateNameUniqueness(String name) {
        Product product = productGateway.findByName(name);

        if(product != null) {
            throw new ProductExistsException("The product with name " + name +  " already exists");
        }
    }

    public void validateCodeUniqueness(String code) {
        Product product = productGateway.findByCode(code);

        if(product != null) {
            throw new ProductExistsException("The product with code " + code +  " already exists");
        }
    }
}
