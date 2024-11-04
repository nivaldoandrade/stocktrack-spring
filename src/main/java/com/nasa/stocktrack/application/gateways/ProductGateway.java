package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Product;

import java.util.UUID;

public interface ProductGateway {

    PaginatedList<Product> list(Integer page, Integer size, String orderBy, String search);

    Product create(Product product);

    Product findByName(String name);

    Product findByCode(String code);

    Product findById(UUID id);

    Product findByIdWithoutWarehouses(UUID uuid);

    Product findFirstByCategoryId(UUID categoryId);

    void update(Product product);

    void delete(Product product);
}
