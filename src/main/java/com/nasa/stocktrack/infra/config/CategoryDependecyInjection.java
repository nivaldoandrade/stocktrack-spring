package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.application.usecases.category.CreateCategoryUseCase;
import com.nasa.stocktrack.application.usecases.category.ListCategoryUseCase;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryDependecyInjection {

    @Bean
    CreateCategoryUseCase createCategoryUseCase(CategoryGateway categoryGateway) {
        return new CreateCategoryUseCase(categoryGateway);
    }

    @Bean
    ShowCategoryUseCase showCategoryUseCase(CategoryGateway categoryGateway) {
        return new ShowCategoryUseCase(categoryGateway);
    }

    @Bean
    ListCategoryUseCase listCategoryUseCase(CategoryGateway categoryGateway) {
        return new ListCategoryUseCase(categoryGateway);
    }
}
