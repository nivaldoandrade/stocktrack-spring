package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import com.nasa.stocktrack.infra.persistence.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ProductRepositoryGateways implements ProductGateway {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductRepositoryGateways(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product create(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);

        productEntity = productRepository.save(productEntity);

        return productMapper.toDomain(productEntity);
    }

    @Override
    public Product findByName(String name) {
        Optional<ProductEntity> productEntity = productRepository.findByName(name);

        return productEntity.map(productMapper::toDomain).orElse(null);
    }

    @Override
    public Product findByCode(String code) {
        Optional<ProductEntity> productEntity = productRepository.findByCode(code);

        return productEntity.map(productMapper::toDomain).orElse(null);
    }

    @Override
    public Product findById(UUID id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        return productEntity.map(productMapper::toDomain).orElse(null);
    }
}
