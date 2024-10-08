package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.services.ProductService;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.application.usecases.product.CreateProductUseCase;
import com.nasa.stocktrack.application.usecases.product.ListProductUseCase;
import com.nasa.stocktrack.application.usecases.product.ShowProductUseCase;
import com.nasa.stocktrack.application.usecases.product.UpdateProductUseCase;
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

    @Bean
    ShowProductUseCase showProductUseCase(ProductGateway productGateway) {
        return new ShowProductUseCase(productGateway);
    }

    @Bean
    ListProductUseCase listProductUseCase(ProductGateway productGateway) {
        return new ListProductUseCase(productGateway);
    }

    @Bean
    UpdateProductUseCase updateProductUseCase(
            ProductGateway productGateway,
            ProductService productService,
            ShowCategoryUseCase showCategoryUseCase
    ) {
        return new UpdateProductUseCase(productGateway, productService, showCategoryUseCase);
    }

    @Bean
    ProductService productService(ProductGateway productGateway) {
        return new ProductService(productGateway);
    }
}
