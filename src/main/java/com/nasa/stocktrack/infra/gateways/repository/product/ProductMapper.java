package com.nasa.stocktrack.infra.gateways.repository.product;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.infra.gateways.repository.productWarehouse.ProductWarehouseMapper;
import com.nasa.stocktrack.infra.gateways.repository.category.CategoryMapper;
import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    private final ProductWarehouseMapper productWarehouseMapper;
    private final CategoryMapper categoryMapper;


    public ProductMapper(
            ProductWarehouseMapper productWarehouseMapper,
            CategoryMapper categoryMapper
    ) {
        this.productWarehouseMapper = productWarehouseMapper;
        this.categoryMapper = categoryMapper;
    }

    ProductEntity toEntity(Product product) {
        CategoryEntity category = categoryMapper.toEntity(product.getCategory());

        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getBrand(),
                product.getImage(),
                category
        );
    }

    Product toDomain(ProductEntity productEntity) {
        Category category = categoryMapper.toDomain(productEntity.getCategory());
        List<ProductWarehouse> productWarehouses = productWarehouseMapper.toListDomain(productEntity.getWarehouses());

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCode(),
                productEntity.getBrand(),
                productEntity.getImage(),
                productEntity.getImageUrl(),
                category,
                productWarehouses
        );
    }

    Product toDomainWithoutWarehouses(ProductEntity productEntity) {
        Category category = categoryMapper.toDomain(productEntity.getCategory());

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCode(),
                productEntity.getBrand(),
                productEntity.getImage(),
                productEntity.getImageUrl(),
                category
        );
    }

    PaginatedList<Product> toListDomain(Page<ProductEntity> productEntities) {
        List<Product> products = productEntities.map(this::toDomain).toList();

        return new PaginatedList<Product>(
                products,
                productEntities.getTotalElements(),
                productEntities.getTotalPages()
        );
    }
}
