package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.FileData;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.exceptions.ProductExistsException;
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

    public void saveProductImage(InputStream content, String fileName) {
        fileStorageService.saveFile(content, fileName);
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
