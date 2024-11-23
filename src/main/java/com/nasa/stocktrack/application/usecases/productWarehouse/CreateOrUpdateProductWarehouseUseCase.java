package com.nasa.stocktrack.application.usecases.productWarehouse;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.product.ShowProductUseCase;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrUpdateProductWarehouseUseCase {

    private final ShowProductUseCase showProductUseCase;
    private final ProductWarehouseService productWarehouseService;
    private final ProductGateway productGateway;

    public CreateOrUpdateProductWarehouseUseCase(
            ShowProductUseCase showProductUseCase,
            ProductWarehouseService productWarehouseService, ProductGateway productGateway
    ) {
        this.showProductUseCase = showProductUseCase;
        this.productWarehouseService = productWarehouseService;
        this.productGateway = productGateway;
    }

    @Transactional
    public void execute(UUID productId, List<ProductWarehouse> productWarehouses) {
        Product productExisting = showProductUseCase.execute(productId);

        Map<UUID, ProductWarehouse> warehouseMap = productExisting.getProductWarehouses().stream()
                .collect(Collectors.toMap(
                        pw -> pw.getWarehouse().getId(),
                        pw -> pw
                ));

        productWarehouses.forEach(newWarehouse ->
                warehouseMap.put(newWarehouse.getWarehouse().getId(), newWarehouse));

        int total = warehouseMap.values().stream()
                        .mapToInt(ProductWarehouse::getQuantity).sum();

        if(!productExisting.getTotal().equals(total)) {
            productGateway.updateTotal(productId, total);
        }

        List<ProductWarehouse> warehouses = productWarehouseService.mapToProductWarehouses(
                productWarehouses,
                productExisting
        );

        productWarehouseService.saveAll(warehouses);
    }
}
