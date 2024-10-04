package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Warehouse;

public interface WarehouseGateway {

    Warehouse create(Warehouse warehouse);

    Warehouse findByName(String name);
}
