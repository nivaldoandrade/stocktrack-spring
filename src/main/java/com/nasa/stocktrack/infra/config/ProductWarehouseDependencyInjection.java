package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.product.ShowProductUseCase;
import com.nasa.stocktrack.application.usecases.productWarehouse.CreateOrUpdateProductWarehouseUseCase;
import com.nasa.stocktrack.application.usecases.productWarehouse.DeleteProductWarehouseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductWarehouseDependencyInjection {

    @Bean
    ProductWarehouseService productWarehouseService(
            WarehouseGateway warehouseGateway,
            ProductWarehouseGateway productWarehouseGateway
    ) {
        return new ProductWarehouseService(warehouseGateway, productWarehouseGateway);
    }

    @Bean
    CreateOrUpdateProductWarehouseUseCase createOrUpdateProductWarehouseUseCase(
            ProductWarehouseGateway productWarehouseGateway,
            ShowProductUseCase showProductUseCase,
            ProductWarehouseService productWarehouseService
    ) {
        return new CreateOrUpdateProductWarehouseUseCase(
                productWarehouseGateway,
                showProductUseCase,
                productWarehouseService
        );
    }

    @Bean
    DeleteProductWarehouseUseCase deleteProductWarehouseUseCase(ProductWarehouseGateway productWarehouseGateway) {
        return new DeleteProductWarehouseUseCase(productWarehouseGateway);
    }
}
