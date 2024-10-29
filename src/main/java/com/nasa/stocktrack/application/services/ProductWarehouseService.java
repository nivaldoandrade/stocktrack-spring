package com.nasa.stocktrack.application.services;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.exceptions.DuplicateWarehouseIdException;
import com.nasa.stocktrack.domain.exceptions.WarehouseNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProductWarehouseService {

    private final WarehouseGateway warehouseGateway;

    private final ProductWarehouseGateway productWarehouseGateway;

    public ProductWarehouseService(
            WarehouseGateway warehouseGateway,
            ProductWarehouseGateway productWarehouseGateway
    ) {
        this.warehouseGateway = warehouseGateway;
        this.productWarehouseGateway = productWarehouseGateway;
    }

    public List<ProductWarehouse> saveAll(List<ProductWarehouse> productWarehouses) {
        return productWarehouseGateway.saveAll(productWarehouses);
    }

    public List<ProductWarehouse> mapToProductWarehouses(
            List<ProductWarehouse> inputProductWarehouse,
            Product product
    ) {
        Set<UUID> warehouseIds = new HashSet<>();

        return inputProductWarehouse
                .stream()
                .map(ipw -> createProductWarehouse(ipw, product, warehouseIds))
                .toList();
    }

    private ProductWarehouse createProductWarehouse(
            ProductWarehouse inputProductWarehouse,
            Product product,
            Set<UUID> warehouseIdsExisting
    ) {
        UUID warehouseId = inputProductWarehouse.getWarehouse().getId();

        if(warehouseIdsExisting.contains(warehouseId)) {
            throw new DuplicateWarehouseIdException(
                    "stockPerWarehouse",
                    "Duplicate warehouse ID: " + warehouseId
            );
        }

        Warehouse warehouse = warehouseGateway.findById(warehouseId);

        if(warehouse == null) {
            throw new WarehouseNotFoundException();
        }

        warehouseIdsExisting.add(warehouseId);

        return new ProductWarehouse(
                product,
                warehouse,
                inputProductWarehouse.getQuantity(),
                inputProductWarehouse.getLocation()
        );
    }
}
