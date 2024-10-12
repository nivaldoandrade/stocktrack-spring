package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.ProductWarehouse;

import java.util.List;

public interface ProductWarehouseGateway {

    List<ProductWarehouse> saveAll(List<ProductWarehouse> productWarehouses);
}
