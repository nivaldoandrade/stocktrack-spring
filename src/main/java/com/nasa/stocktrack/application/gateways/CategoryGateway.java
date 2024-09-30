package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Category;

import java.util.UUID;

public interface CategoryGateway {

    Category create(Category category);

    Category findByName(String name);

    Category findById(UUID id);
}

