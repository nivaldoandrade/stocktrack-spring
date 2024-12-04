package com.nasa.stocktrack.infra.config.application;

import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.gateways.WarehouseGateway;
import com.nasa.stocktrack.application.usecases.warehouse.*;
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

    @Bean
    UpdateWarehouseUseCase updateWarehouseUseCase(
            WarehouseGateway warehouseGateway,
            ShowWarehouseUseCase showWarehouseUseCase
    ) {
        return new UpdateWarehouseUseCase(warehouseGateway, showWarehouseUseCase);
    }

    @Bean
    DeleteWarehouseUseCase deleteWarehouseUseCase(
            WarehouseGateway warehouseGateway,
            ShowWarehouseUseCase showWarehouseUseCase,
            ProductWarehouseGateway productWarehouseGateway
    ) {
        return new DeleteWarehouseUseCase(warehouseGateway, showWarehouseUseCase, productWarehouseGateway);
    }
}
