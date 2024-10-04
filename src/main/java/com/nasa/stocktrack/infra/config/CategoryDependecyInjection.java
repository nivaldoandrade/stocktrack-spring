package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.CategoryGateway;
import com.nasa.stocktrack.application.usecases.category.*;
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

    @Bean
    UpdateCategoryUseCase updateCategoryUseCase(CategoryGateway categoryGateway, ShowCategoryUseCase showCategoryUseCase) {
        return new UpdateCategoryUseCase(categoryGateway, showCategoryUseCase);
    }

    @Bean
    DeleteCategoryUseCase deleteCategoryUseCase(CategoryGateway categoryGateway) {
        return new DeleteCategoryUseCase(categoryGateway);
    }
}