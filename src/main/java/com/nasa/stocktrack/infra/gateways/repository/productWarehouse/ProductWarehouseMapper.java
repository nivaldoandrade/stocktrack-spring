package com.nasa.stocktrack.infra.gateways.repository.productWarehouse;

import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.entities.Warehouse;
import com.nasa.stocktrack.infra.gateways.repository.warehouse.WarehouseMapper;
import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import com.nasa.stocktrack.infra.persistence.entities.ProductWarehouseEntity;
import com.nasa.stocktrack.infra.persistence.entities.WarehouseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ProductWarehouseMapper {

    private final WarehouseMapper warehouseMapper;

    public ProductWarehouseMapper(WarehouseMapper warehouseMapper) {
        this.warehouseMapper = warehouseMapper;
    }

    public ProductWarehouseEntity toEntity(ProductWarehouse productWarehouse) {
        ProductWarehouseEntity productWarehouseEntity = new ProductWarehouseEntity();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productWarehouse.getProduct().getId());
        WarehouseEntity warehouseEntity = warehouseMapper.toEntity(productWarehouse.getWarehouse());


        productWarehouseEntity.setProductEntity(productEntity);
        productWarehouseEntity.setWarehouseEntity(warehouseEntity);
        productWarehouseEntity.setQuantity(productWarehouse.getQuantity());
        productWarehouseEntity.setLocation(productWarehouse.getLocation());

        return productWarehouseEntity;
    }

    public ProductWarehouse toDomain(ProductWarehouseEntity productWarehouseEntity) {
        Warehouse warehouse = warehouseMapper.toDomain(productWarehouseEntity.getWarehouseEntity());

        return new ProductWarehouse(
                warehouse,
                productWarehouseEntity.getQuantity(),
                productWarehouseEntity.getLocation()
        );
    }

    public ProductWarehouse toDomainOnlyProductWarehouseIds(
            UUID productId,
            UUID warehouseId
    ) {
        Product product = new Product(productId);
        Warehouse warehouse = new Warehouse(warehouseId);

        return new ProductWarehouse(
                product,
                warehouse
        );
    }

    public List<ProductWarehouseEntity> toListEntity(List<ProductWarehouse> productWarehouses) {
        return productWarehouses.stream().map(this::toEntity).toList();
    }

    public List<ProductWarehouse> toListDomain(List<ProductWarehouseEntity> productWarehouseEntities) {
       return productWarehouseEntities.stream().map(this::toDomain).toList();
    }
}
