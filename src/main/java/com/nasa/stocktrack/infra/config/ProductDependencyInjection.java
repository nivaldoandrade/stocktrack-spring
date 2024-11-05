package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.services.FileStorageService;
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
    CreateProductUseCase createProductUseCase(ProductService productService) {
        return new CreateProductUseCase(productService);
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
    DeleteProductUseCase deleteProductUseCase(ProductService productService) {
        return new DeleteProductUseCase(productService);
    }

    @Bean
    ProductService productService(
            ProductGateway productGateway,
            ShowCategoryUseCase showCategoryUseCase,
            FileStorageService fileStorageService,
            ProductWarehouseService productWarehouseService
    ) {
        return new ProductService(
                productGateway,
                showCategoryUseCase,
                fileStorageService,
                productWarehouseService
        );
    }
}
