package com.nasa.stocktrack.interfaces.dtos.product;

import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.Product;
import com.nasa.stocktrack.domain.entities.ProductWarehouse;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.dtos.productWarehouse.CreateProductWarehouseRequestDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public record CreateProductRequestDTO(

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Code is required")
        String code,

        @NotBlank(message = "Brand is required")
        String brand,

        @ValidUUID
        @NotNull(message = "Category id is required")
        String category_id,

        @NotEmpty(message = "StockPerWarehouse is required or must be in an invalid format")
        List<@Valid CreateProductWarehouseRequestDTO> stockPerWarehouse,

        MultipartFile image
) {

        public static Product toDomain(CreateProductRequestDTO createProductRequestDTO) {
                List<ProductWarehouse> productWarehouses =
                        createProductRequestDTO.stockPerWarehouse
                                .stream()
                                .map(CreateProductWarehouseRequestDTO::toDomain)
                                .toList();


                return new Product(
                        createProductRequestDTO.name,
                        createProductRequestDTO.code,
                        createProductRequestDTO.brand(),
                        new Category(UUID.fromString(createProductRequestDTO.category_id)),
                        productWarehouses
                );
        }

}
