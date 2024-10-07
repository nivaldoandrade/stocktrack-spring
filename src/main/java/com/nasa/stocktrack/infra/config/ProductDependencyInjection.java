package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.application.usecases.product.CreateProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDependencyInjection {

    @Bean
    CreateProductUseCase createProductUseCase(
            ProductGateway productGateway,
            ShowCategoryUseCase showCategoryUseCase
    ) {
        return new CreateProductUseCase(productGateway, showCategoryUseCase);
    }
}
