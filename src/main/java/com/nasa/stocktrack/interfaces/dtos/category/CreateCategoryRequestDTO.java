package com.nasa.stocktrack.interfaces.dtos.category;

import com.nasa.stocktrack.domain.entities.Category;
import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequestDTO(

        @NotBlank(message = "Name is required")
        String name
) {

   public static Category toDomain(CreateCategoryRequestDTO createCategoryRequestDTO) {
      return new Category(
         createCategoryRequestDTO.name()
      );
   }
}
