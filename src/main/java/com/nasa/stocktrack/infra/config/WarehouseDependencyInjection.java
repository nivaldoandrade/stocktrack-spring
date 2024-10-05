package com.nasa.stocktrack.infra.config;

import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.application.usecases.warehouse.CreateWarehouseUseCase;
import com.nasa.stocktrack.application.usecases.warehouse.ListWarehouseUseCase;
import com.nasa.stocktrack.application.usecases.warehouse.ShowWarehouseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WarehouseDependencyInjection {

    @Bean
    CreateWarehouseUseCase createWarehouseUseCase(WarehouseGateway warehouseGateway) {
        return new CreateWarehouseUseCase(warehouseGateway);
    }

    @Bean
    ShowWarehouseUseCase showWarehouseUseCase(WarehouseGateway warehouseGateway) {
        return new ShowWarehouseUseCase(warehouseGateway);
    }

    @Bean
    ListWarehouseUseCase listWarehouseUseCase(WarehouseGateway warehouseGateway) {
        return new ListWarehouseUseCase(warehouseGateway);
    }
}
