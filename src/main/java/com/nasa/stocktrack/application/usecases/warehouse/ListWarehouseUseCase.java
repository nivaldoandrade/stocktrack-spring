package com.nasa.stocktrack.application.usecases.warehouse;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

public class ListWarehouseUseCase {

    private final WarehouseGateway warehouseGateway;


    public ListWarehouseUseCase(WarehouseGateway warehouseGateway) {
        this.warehouseGateway = warehouseGateway;
    }

    public PaginatedList<Warehouse> execute(Integer page, Integer size, OrderByEnum orderBy, String search) {
        return warehouseGateway.list(page, size, orderBy, search);
    }
}
