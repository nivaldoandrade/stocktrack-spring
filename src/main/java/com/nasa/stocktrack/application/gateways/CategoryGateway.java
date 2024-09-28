package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.entities.Category;

public interface CategoryGateway {

    Category create(Category category);

    Category findByName(String name);
}

