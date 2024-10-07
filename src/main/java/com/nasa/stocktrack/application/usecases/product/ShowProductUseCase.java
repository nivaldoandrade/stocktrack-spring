package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductNotFoundException;

import java.util.UUID;

public class ShowProductUseCase {

    private final ProductGateway productGateway;

    public ShowProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public Product execute(UUID id) {
        Product product = productGateway.findById(id);

        if(product == null) {
            throw new ProductNotFoundException();
        }

        return product;
    }
}
