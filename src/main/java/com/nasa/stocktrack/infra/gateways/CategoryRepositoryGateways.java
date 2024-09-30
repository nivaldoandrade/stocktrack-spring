package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.ListCategory;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import com.nasa.stocktrack.infra.persistence.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class CategoryRepositoryGateways implements CategoryGateway {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryRepositoryGateways(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public ListCategory list(Integer page, Integer size, OrderByEnum orderBy, String search) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy.name())
            ? Sort.Direction.DESC
            : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "name"));

        Page<CategoryEntity> categoriesEntity = categoryRepository.findAll(search, pageable);

        return categoryMapper.toListDomain(categoriesEntity);
    }

    @Override
    public Category create(Category category) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(category);
        categoryEntity = categoryRepository.save(categoryEntity);

        return categoryMapper.toDomain(categoryEntity);
    }

    @Override
    public Category findByName(String name) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(name);

        return categoryEntity.map(categoryMapper::toDomain).orElse(null);
    }

    @Override
    public Category findById(UUID id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);

        return categoryEntity.map(categoryMapper::toDomain).orElse(null);
    }

    @Override
    public void update(Category category) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(category);

        categoryRepository.save(categoryEntity);
    }
}
