package com.nasa.stocktrack.application.usecases.product;

import com.nasa.stocktrack.application.gateways.ProductGateway;
import com.nasa.stocktrack.application.gateways.ProductWarehouseGateway;
import com.nasa.stocktrack.application.services.ProductWarehouseService;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.domain.exceptions.ProductExistsException;
import jakarta.transaction.Transactional;

import java.util.List;

public class CreateProductUseCase {

    private final ProductGateway productGateway;

    private final ShowCategoryUseCase showCategoryUseCase;

    private final ProductWarehouseService productWarehouseService;

    private final ProductWarehouseGateway productWarehouseGateway;

    public CreateProductUseCase(
            ProductGateway productGateway,
            ShowCategoryUseCase showCategoryUseCase,
            ProductWarehouseService productWarehouseService,
            ProductWarehouseGateway productWarehouseGateway
    ) {
        this.productGateway = productGateway;
        this.showCategoryUseCase = showCategoryUseCase;
        this.productWarehouseService = productWarehouseService;
        this.productWarehouseGateway = productWarehouseGateway;
    }

    @Transactional
    public Product execute(Product product) {
        Product productByNameExisting = productGateway.findByName(product.getName());

        if(productByNameExisting != null) {
            throw new ProductExistsException("The product with this name already exists");
        }

        Product productByCodeExisting = productGateway.findByCode(product.getCode());

        if(productByCodeExisting != null) {
            throw new ProductExistsException("The product with code is already exists");
        }

        Category category = showCategoryUseCase.execute(product.getCategory().getId());

        product.setCategory(category);

        Product newProduct =  productGateway.create(product);

        List<ProductWarehouse> productWarehouses = productWarehouseService.mapToProductWarehouses(
                product.getProductWarehouses(),
                newProduct
        );

        productWarehouses =  productWarehouseGateway.saveAll(productWarehouses);

        newProduct.setProductWarehouses(productWarehouses);

        return newProduct;
    }
}
