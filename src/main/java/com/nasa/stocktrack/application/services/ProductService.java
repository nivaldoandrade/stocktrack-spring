package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.exceptions.ProductExistsException;
import com.nasa.stocktrack.domain.exceptions.ProductNotFoundException;
import jakarta.transaction.Transactional;

import java.io.InputStream;
import java.util.List;

public class ProductService {

    private final ProductGateway productGateway;

    private final ShowCategoryUseCase showCategoryUseCase;

    private final FileStorageService fileStorageService;

    private final ProductWarehouseService productWarehouseService;

    public ProductService(
            ProductGateway productGateway,
            ShowCategoryUseCase showCategoryUseCase,
            FileStorageService fileStorageService,
            ProductWarehouseService productWarehouseService
    ) {
        this.productGateway = productGateway;
        this.showCategoryUseCase = showCategoryUseCase;
        this.fileStorageService = fileStorageService;
        this.productWarehouseService = productWarehouseService;
    }

    @Transactional
    public Product create(Product product, FileData fileData) {
        this.validateNameUniqueness(product.getName());
        this.validateCodeUniqueness(product.getCode());

        String fileName = fileData != null
                ? fileStorageService.generateFilename(fileData.getFilename())
                : null;

        product.setImage(fileName);

        Category category = showCategoryUseCase.execute(product.getCategory().getId());

        product.setCategory(category);

        Product newProduct =  productGateway.create(product);

        List<ProductWarehouse> productWarehouses = productWarehouseService.mapToProductWarehouses(
                product.getProductWarehouses(),
                newProduct
        );

        productWarehouses =  productWarehouseService.saveAll(productWarehouses);

        newProduct.setProductWarehouses(productWarehouses);

        return newProduct;
    }

    @Transactional
    public void update(Product product, FileData fileData) {
        Product productExisting = productGateway.findById(product.getId());

        if(productExisting == null) {
            throw new ProductNotFoundException();
        }

        boolean hasSameCategoryId = product.getCategory().getId().equals(productExisting.getCategory().getId());

        if(!hasSameCategoryId) {
            Category newCategory = showCategoryUseCase.execute(product.getCategory().getId());

            product.setCategory(newCategory);
        }

        if(!product.getName().equals(productExisting.getName())) {
            this.validateNameUniqueness(product.getName());

        }

        if(!product.getCode().equals(productExisting.getCode())) {
            this.validateCodeUniqueness(product.getCode());

        }

        String fileName = fileData != null
                ? fileStorageService.generateFilename(fileData.getFilename())
                : productExisting.getImage();

        product.setImage(fileName);

        productGateway.update(product);

        if(fileData != null) {
            this.saveProductImage(fileData.getContent(), fileName);

            if(productExisting.getImage() != null) {
                this.deleteProductImage(productExisting.getImage());
            }
        }
    }

    public void saveProductImage(InputStream content, String fileName) {
        fileStorageService.saveFile(content, fileName);
    }

    public void deleteProductImage(String fileName) {
        fileStorageService.deleteFile(fileName);
    }

    private void validateNameUniqueness(String name) {
        Product product = productGateway.findByName(name);

        if(product != null) {
            throw new ProductExistsException("The product with name " + name +  " already exists");
        }
    }

    private void validateCodeUniqueness(String code) {
        Product product = productGateway.findByCode(code);

        if(product != null) {
            throw new ProductExistsException("The product with code " + code +  " already exists");
        }
    }
}
