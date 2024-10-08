package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.exceptions.ProductExistsException;

public class ProductService {

    private final ProductGateway productGateway;

    public ProductService(ProductGateway productGateway) {
        this.productGateway = productGateway;
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
