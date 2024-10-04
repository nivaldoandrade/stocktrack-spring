package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.application.usecases.warehouse.CreateWarehouseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarehouseDependencyInjection {

    @Bean
    CreateWarehouseUseCase createWarehouseUseCase(WarehouseGateway warehouseGateway) {
        return new CreateWarehouseUseCase(warehouseGateway);
    }
}
