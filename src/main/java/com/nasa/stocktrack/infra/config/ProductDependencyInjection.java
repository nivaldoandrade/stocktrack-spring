package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.services.ProductService;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.application.usecases.product.*;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDependencyInjection {

    @Bean
    @Transactional
    CreateProductUseCase createProductUseCase(
            ProductGateway productGateway,
            ShowCategoryUseCase showCategoryUseCase,
            ProductWarehouseService productWarehouseService,
            ProductWarehouseGateway productWarehouseGateway
    ) {
        return new CreateProductUseCase(productGateway, showCategoryUseCase, productWarehouseService, productWarehouseGateway);
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
    DeleteProductUseCase deleteProductUseCase(ProductGateway productGateway) {
        return new DeleteProductUseCase(productGateway);
    }

    @Bean
    ProductService productService(ProductGateway productGateway) {
        return new ProductService(productGateway);
    }
}
