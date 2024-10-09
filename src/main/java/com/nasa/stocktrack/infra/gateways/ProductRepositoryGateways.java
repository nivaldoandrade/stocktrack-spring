package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.infra.persistence.entities.ProductEntity;
import com.nasa.stocktrack.infra.persistence.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.nasa.stocktrack.infra.persistence.specifications.ProductSpecification.byCode;
import static com.nasa.stocktrack.infra.persistence.specifications.ProductSpecification.byName;

@Component
public class ProductRepositoryGateways implements ProductGateway {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductRepositoryGateways(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public PaginatedList<Product> list(Integer page, Integer size, String orderBy, String search) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "name"));

        Page<ProductEntity> productEntities = productRepository.findAll(
                byName(search).or(byCode(search)),
                pageable
        );

        return productMapper.toListDomain(productEntities);
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

    @Override
    public void update(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);

        productRepository.save(productEntity);
    }

    @Override
    public void delete(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);

        productRepository.delete(productEntity);
    }
}
