package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.persistence.entities.WarehouseEntity;
import com.nasa.stocktrack.infra.persistence.repositories.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseRepositoryGateways implements WarehouseGateway {

    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;

    public WarehouseRepositoryGateways(
            WarehouseRepository warehouseRepository,
            WarehouseMapper warehouseMapper
    ) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public PaginatedList<Warehouse> list(Integer page, Integer size, OrderByEnum orderBy, String search) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy.name())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "name"));

        Page<WarehouseEntity> warehouseEntities = warehouseRepository.findAll(search, pageable);

        for(WarehouseEntity w: warehouseEntities) {
            System.out.println(w.getName());
        }

        return warehouseMapper.toListDomain(warehouseEntities);
    }

    @Override
    public Warehouse create(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = warehouseMapper.toEntity(warehouse);

        warehouseEntity = warehouseRepository.save(warehouseEntity);

        return warehouseMapper.toDomain(warehouseEntity);
    }

    @Override
    public Warehouse findByName(String name) {
        Optional<WarehouseEntity> warehouseEntity = warehouseRepository.findByName(name);

        return warehouseEntity.map(warehouseMapper::toDomain).orElse(null);
    }

    @Override
    public Warehouse findById(UUID id) {
        Optional<WarehouseEntity> warehouseEntity = warehouseRepository.findById(id);

        return warehouseEntity.map(warehouseMapper::toDomain).orElse(null);
    }

    @Override
    public void update(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = warehouseMapper.toEntity(warehouse);

        warehouseRepository.save(warehouseEntity);
    }
}
