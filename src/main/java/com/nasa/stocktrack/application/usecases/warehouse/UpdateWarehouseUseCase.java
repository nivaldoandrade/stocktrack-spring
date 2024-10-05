package com.nasa.stocktrack.application.usecases.warehouse;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.exceptions.WarehouseExistsException;

public class UpdateWarehouseUseCase {
    private final WarehouseGateway warehouseGateway;

    private final ShowWarehouseUseCase showWarehouseUseCase;

    public UpdateWarehouseUseCase(WarehouseGateway warehouseGateway, ShowWarehouseUseCase showWarehouseUseCase) {
        this.warehouseGateway = warehouseGateway;
        this.showWarehouseUseCase = showWarehouseUseCase;
    }

    public void execute(Warehouse warehouse) {
        Warehouse warehouseExisting = showWarehouseUseCase.execute(warehouse.getId());

        if(warehouseExisting.getName().equals(warehouse.getName())) {
            return;
        }

        Warehouse warehouseNameExisting = warehouseGateway.findByName(warehouse.getName());

        if(warehouseNameExisting != null && !warehouseNameExisting.equals(warehouseExisting)) {
            throw new WarehouseExistsException();
        }

        warehouseExisting.setName(warehouse.getName());

        warehouseGateway.update(warehouseExisting);
    }
}
