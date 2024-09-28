package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.infra.persistence.entities.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    CategoryEntity toEntity(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName(category.getName());

        return categoryEntity;
    }

    Category toDomain(CategoryEntity categoryEntity) {
        Category category = new Category(
                categoryEntity.getId(),
                categoryEntity.getName()
        );

        return category;
    }
}
