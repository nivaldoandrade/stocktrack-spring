package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.ProductWarehouse;

import java.util.List;
import java.util.UUID;

public interface ProductWarehouseGateway {

    List<ProductWarehouse> saveAll(List<ProductWarehouse> productWarehouses);

    ProductWarehouse getProductWarehouseIdPair(UUID productId, UUID warehouseID);

    boolean checkWarehouseInUseById(UUID warehouseId);

    void delete(ProductWarehouse productWarehouse);
}
