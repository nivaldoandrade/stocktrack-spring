package com.nasa.stocktrack.infra.config.application;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.services.FileStorageService;
import com.nasa.stocktrack.application.services.ProductService;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.application.usecases.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDependencyInjection {

    @Bean
    CreateProductUseCase createProductUseCase(
            ProductService productService,
            ProductWarehouseService productWarehouseService
    ) {
        return new CreateProductUseCase(
                productService,
                productWarehouseService
        );
    }

    @Bean
    GetImageProductUseCase getImageProductUseCase(FileStorageService fileStorageService) {
        return new GetImageProductUseCase(fileStorageService);
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
    UpdateProductUseCase updateProductUseCase(ProductService productService) {
        return new UpdateProductUseCase(productService);
    }

    @Bean
    DeleteProductUseCase deleteProductUseCase(ProductGateway productGateway, FileStorageService fileStorageService) {
        return new DeleteProductUseCase(productGateway, fileStorageService);
    }

    @Bean
    ProductService productService(
            ProductGateway productGateway,
            FileStorageService fileStorageService,
            ShowCategoryUseCase showCategoryUseCase
    ) {
        return new ProductService(
                productGateway,
                fileStorageService,
                showCategoryUseCase
        );
    }
}
