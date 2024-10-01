package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.ListCategory;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

import java.util.UUID;

public interface CategoryGateway {

    ListCategory list(Integer page, Integer size, OrderByEnum orderBy, String search);

    Category create(Category category);

    Category findByName(String name);

    Category findById(UUID id);

    void update(Category category);

    void delete(Category category);
}

