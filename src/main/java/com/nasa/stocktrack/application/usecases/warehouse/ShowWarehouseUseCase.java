package com.nasa.stocktrack.application.usecases.warehouse;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.exceptions.WarehouseNotFoundException;

import java.util.UUID;

public class ShowWarehouseUseCase {

    private final WarehouseGateway warehouseGateway;

    public ShowWarehouseUseCase(WarehouseGateway warehouseGateway) {
        this.warehouseGateway = warehouseGateway;
    }

    public Warehouse execute(UUID id) {
        Warehouse warehouse = warehouseGateway.findById(id);

        if(warehouse == null) {
            throw new WarehouseNotFoundException();
        }

        return warehouse;
    }
}
