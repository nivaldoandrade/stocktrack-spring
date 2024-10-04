package com.nasa.stocktrack.application.usecases.warehouse;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.exceptions.WarehouseExistsException;

public class CreateWarehouseUseCase {

    private final WarehouseGateway warehouseGateway;

    public CreateWarehouseUseCase(WarehouseGateway warehouseGateway) {
        this.warehouseGateway = warehouseGateway;
    }

    public Warehouse execute(Warehouse warehouse) {
        Warehouse warehouseExisting = warehouseGateway.findByName(warehouse.getName());

        if(warehouseExisting != null) {
            throw new WarehouseExistsException();
        }

        return warehouseGateway.create(warehouse);
    }
}
