package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.ProductGateway;
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
            ShowProductUseCase showProductUseCase,
            ProductWarehouseService productWarehouseService,
            ProductGateway productGateway
    ) {
        return new CreateOrUpdateProductWarehouseUseCase(
                showProductUseCase,
                productWarehouseService,
                productGateway
        );
    }

    @Bean
    DeleteProductWarehouseUseCase deleteProductWarehouseUseCase(ProductWarehouseGateway productWarehouseGateway) {
        return new DeleteProductWarehouseUseCase(productWarehouseGateway);
    }
}
