package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

import java.util.UUID;

public interface WarehouseGateway {

    PaginatedList<Warehouse> list(Integer page, Integer size, OrderByEnum orderBy, String search);

    Warehouse create(Warehouse warehouse);

    Warehouse findByName(String name);

    Warehouse findById(UUID id);
}
