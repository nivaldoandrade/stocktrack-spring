package com.nasa.stocktrack.infra.gateways.repository.category;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());

        return categoryEntity;
    }

    public Category toDomain(CategoryEntity categoryEntity) {
        Category category = new Category(
                categoryEntity.getId(),
                categoryEntity.getName()
        );

        return category;
    }

    public PaginatedList<Category> toListDomain(Page<CategoryEntity> categoryEntityPage) {
        List<Category> categories = categoryEntityPage.stream().map(this::toDomain).toList();

        return new PaginatedList<Category>(
                categories,
                categoryEntityPage.getTotalElements(),
                categoryEntityPage.getTotalPages()
        );
    }
}
