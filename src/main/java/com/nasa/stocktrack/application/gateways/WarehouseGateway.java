package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Warehouse;

import java.util.UUID;

public interface WarehouseGateway {

    Warehouse create(Warehouse warehouse);

    Warehouse findByName(String name);

    Warehouse findById(UUID id);
}
