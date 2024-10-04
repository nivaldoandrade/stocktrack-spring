package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.infra.persistence.entities.WarehouseEntity;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    WarehouseEntity toEntity(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = new WarehouseEntity();

        warehouseEntity.setId(warehouse.getId());
        warehouseEntity.setName(warehouse.getName());

        return warehouseEntity;
    }

    Warehouse toDomain(WarehouseEntity warehouseEntity) {
        return  new Warehouse(
                warehouseEntity.getId(),
                warehouseEntity.getName()
        );
    }
}
