package com.nasa.stocktrack.application.usecases.warehouse;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.exceptions.WarehouseInUseException;

import java.util.UUID;

public class DeleteWarehouseUseCase {

    private final WarehouseGateway warehouseGateway;

    private final ShowWarehouseUseCase showWarehouseUseCase;

    private final ProductWarehouseGateway productWarehouseGateway;

    public DeleteWarehouseUseCase(
            WarehouseGateway warehouseGateway,
            ShowWarehouseUseCase showWarehouseUseCase,
            ProductWarehouseGateway productWarehouseGateway
    ) {
        this.warehouseGateway = warehouseGateway;
        this.showWarehouseUseCase = showWarehouseUseCase;
        this.productWarehouseGateway = productWarehouseGateway;
    }

    public void execute(UUID id) {
        Warehouse warehouse = showWarehouseUseCase.execute(id);

        boolean warehouseInUse = productWarehouseGateway.checkWarehouseInUseById(id);

        if(warehouseInUse) {
            throw new WarehouseInUseException();
        }

        warehouseGateway.delete(warehouse);
    }
}
