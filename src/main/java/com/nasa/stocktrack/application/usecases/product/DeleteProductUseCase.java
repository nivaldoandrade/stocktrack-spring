package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.services.FileStorageService;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductNotFoundException;
import org.springframework.transaction.annotation.Transactional;


import java.util.UUID;

public class DeleteProductUseCase {

    private final ProductGateway productGateway;

    private final FileStorageService fileStorageService;

    public DeleteProductUseCase(ProductGateway productGateway, FileStorageService fileStorageService) {
        this.productGateway = productGateway;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    public void execute(UUID id) {
        Product product = productGateway.findById(id);

        if(product == null) {
            throw new ProductNotFoundException();
        }

        productGateway.delete(product);

        if(product.getImage() != null) {
            fileStorageService.deleteFile(product.getImage());
        }
    }
}
