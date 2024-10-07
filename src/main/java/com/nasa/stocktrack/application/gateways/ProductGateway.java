package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Product;

public interface ProductGateway {

    Product create(Product product);

    Product findByName(String name);

    Product findByCode(String code);
}
