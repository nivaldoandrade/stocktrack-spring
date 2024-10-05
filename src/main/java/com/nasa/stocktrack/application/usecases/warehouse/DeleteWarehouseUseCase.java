package com.nasa.stocktrack.application.usecases.warehouse;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.entities.Warehouse;

import java.util.UUID;

public class DeleteWarehouseUseCase {

    private final WarehouseGateway warehouseGateway;

    private final ShowWarehouseUseCase showWarehouseUseCase;

    public DeleteWarehouseUseCase(
            WarehouseGateway warehouseGateway,
            ShowWarehouseUseCase showWarehouseUseCase
    ) {
        this.warehouseGateway = warehouseGateway;
        this.showWarehouseUseCase = showWarehouseUseCase;
    }

    public void execute(UUID id) {
        Warehouse warehouse = showWarehouseUseCase.execute(id);

        warehouseGateway.delete(warehouse);
    }
}
