package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.infra.persistence.entities.WarehouseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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

    PaginatedList<Warehouse> toListDomain(Page<WarehouseEntity> warehouseEntities) {
        List<Warehouse> warehouses = warehouseEntities.stream().map(this::toDomain).toList();

        return new PaginatedList<Warehouse>(
                warehouses,
                warehouseEntities.getTotalElements(),
                warehouseEntities.getTotalPages()
        );
    }
}
