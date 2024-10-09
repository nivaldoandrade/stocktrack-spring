package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductNotFoundException;

import java.util.UUID;

public class DeleteProductUseCase {

    private final ProductGateway productGateway;

    public DeleteProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public void execute(UUID id) {
        Product product = productGateway.findById(id);

        if(product == null) {
            throw new ProductNotFoundException();
        }

        productGateway.delete(product);
    }
}
