package com.nasa.stocktrack.application.usecases.productWarehouse;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.exceptions.ProductWarehouseNotFoundException;

import java.util.UUID;

public class DeleteProductWarehouseUseCase {

    private final ProductWarehouseGateway productWarehouseGateway;

    public DeleteProductWarehouseUseCase(ProductWarehouseGateway productWarehouseGateway) {
        this.productWarehouseGateway = productWarehouseGateway;
    }

    public void execute(UUID productId, UUID warehouseId) {
        ProductWarehouse productWarehouse = productWarehouseGateway.getProductWarehouseIdPair(
                productId,
                warehouseId
        );

        if(productWarehouse == null) {
            throw new ProductWarehouseNotFoundException();
        }

        productWarehouseGateway.delete(productWarehouse);
    }
}
