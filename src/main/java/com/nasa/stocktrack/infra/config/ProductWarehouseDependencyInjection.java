package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.product.ShowProductUseCase;
import com.nasa.stocktrack.application.usecases.productWarehouse.CreateOrUpdateProductWarehouseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductWarehouseDependencyInjection {

    @Bean
    ProductWarehouseService productWarehouseService(WarehouseGateway warehouseGateway) {
        return new ProductWarehouseService(warehouseGateway);
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
}
