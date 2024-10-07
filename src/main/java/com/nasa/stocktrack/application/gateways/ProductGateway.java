package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Product;

import java.util.UUID;

public interface ProductGateway {

    Product create(Product product);

    Product findByName(String name);

    Product findByCode(String code);

    Product findById(UUID id);
}
