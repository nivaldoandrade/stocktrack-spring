package com.nasa.stocktrack.interfaces.dtos.product;

import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.interfaces.dtos.category.CategoryDTO;
import com.nasa.stocktrack.interfaces.dtos.productWarehouse.ProductWarehouseDTO;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record ProductDTO(
        UUID id,

        String name,

        String code,

        String brand,

        String image,

        int quantityTotal,

        String imageUrl,

        CategoryDTO category,

        List<ProductWarehouseDTO> warehouses
) {

    public static ProductDTO toResponse(Product product) {
        List<ProductWarehouseDTO> productWarehouseDTOS = product
                .getProductWarehouses()
                .stream()
                .map(ProductWarehouseDTO::toResponse).toList();

        CategoryDTO categoryDTO = CategoryDTO.toResponse(product.getCategory());

        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .brand(product.getBrand())
                .image(product.getImage())
                .quantityTotal(product.getTotal())
                .imageUrl(product.getImageUrl())
                .category(categoryDTO)
                .warehouses(productWarehouseDTOS)
                .build();
    }
}
