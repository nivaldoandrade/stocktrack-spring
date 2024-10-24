package com.nasa.stocktrack.infra.gateways.repository.productWarehouse;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.infra.persistence.entities.ProductWarehouseEntity;
import com.nasa.stocktrack.infra.persistence.repositories.ProductWarehouseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductWarehouseRepositoryGateways implements ProductWarehouseGateway {

    private final ProductWarehouseRepository productWarehouseRepository;

    private final ProductWarehouseMapper productWarehouseMapper;

    public ProductWarehouseRepositoryGateways(
            ProductWarehouseRepository productWarehouseRepository,
            ProductWarehouseMapper productWarehouseMapper
            ) {
        this.productWarehouseRepository = productWarehouseRepository;
        this.productWarehouseMapper = productWarehouseMapper;
    }

    @Override
    public List<ProductWarehouse> saveAll(List<ProductWarehouse> productWarehouses) {
        List<ProductWarehouseEntity> productWarehouseEntities = productWarehouseMapper.toListEntity(productWarehouses);

        productWarehouseEntities = productWarehouseRepository.saveAll(productWarehouseEntities);

        return productWarehouseMapper.toListDomain(productWarehouseEntities);
    }

    @Override
    public ProductWarehouse getProductWarehouseIdPair(UUID productId, UUID warehouseId) {
        Optional<ProductWarehouseEntity> productWarehouseEntity = productWarehouseRepository.findByProductIdAndWarehouseId(
                productId,
                warehouseId
        );

        if(productWarehouseEntity.isEmpty()) {
            return null;
        }

        return productWarehouseMapper.toDomainOnlyProductWarehouseIds(productId, warehouseId);
    }

    @Override
    public boolean checkWarehouseInUseById(UUID warehouseId) {
        Optional<ProductWarehouseEntity> productWarehouseEntity = productWarehouseRepository.existsByWarehouseId(warehouseId);

        return productWarehouseEntity.isPresent();
    }

    @Override
    public void delete(ProductWarehouse productWarehouse) {
        ProductWarehouseEntity productWarehouseEntity = productWarehouseMapper.toEntity(productWarehouse);

        productWarehouseRepository.delete(productWarehouseEntity);
    }

}
