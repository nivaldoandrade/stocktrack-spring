package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    ProductEntity toEntity(Product product) {
        CategoryEntity category = categoryMapper.toEntity(product.getCategory());

        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getCode(),
                product.getBrand(),
                category
        );
    }

    Product toDomain(ProductEntity productEntity) {
        Category category = categoryMapper.toDomain(productEntity.getCategory());

        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getCode(),
                productEntity.getBrand(),
                category
        );
    }
}
