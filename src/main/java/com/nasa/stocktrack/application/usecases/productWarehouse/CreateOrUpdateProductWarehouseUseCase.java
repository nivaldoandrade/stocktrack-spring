package com.nasa.stocktrack.application.usecases.productWarehouse;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.product.ShowProductUseCase;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;

import java.util.List;
import java.util.UUID;

public class CreateOrUpdateProductWarehouseUseCase {

    private final ProductWarehouseGateway productWarehouseGateway;
    private final ShowProductUseCase showProductUseCase;
    private final ProductWarehouseService productWarehouseService;

    public CreateOrUpdateProductWarehouseUseCase(
            ProductWarehouseGateway productWarehouseGateway,
            ShowProductUseCase showProductUseCase,
            ProductWarehouseService productWarehouseService
    ) {
        this.productWarehouseGateway = productWarehouseGateway;
        this.showProductUseCase = showProductUseCase;
        this.productWarehouseService = productWarehouseService;
    }

    public void execute(UUID productId, List<ProductWarehouse> productWarehouses) {
        Product productExisting = showProductUseCase.execute(productId);

        List<ProductWarehouse> warehouses = productWarehouseService.mapToProductWarehouses(
                productWarehouses,
                productExisting
        );

        productWarehouseGateway.saveAll(warehouses);
    }
}
