package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Product;

public class ListProductUseCase {

    private final ProductGateway productGateway;

    public ListProductUseCase(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    public PaginatedList<Product> execute(Integer page, Integer size, String orderBy, String search) {
        return productGateway.list(page, size, orderBy, search);
    }
}
