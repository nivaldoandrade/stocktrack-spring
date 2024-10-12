package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductWarehouseDependencyInjection {

    @Bean
    ProductWarehouseService productWarehouseService(WarehouseGateway warehouseGateway) {
        return new ProductWarehouseService(warehouseGateway);
    }
}
