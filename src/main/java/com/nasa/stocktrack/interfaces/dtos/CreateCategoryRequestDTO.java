package com.nasa.stocktrack.interfaces.dtos;

import com.nasa.stocktrack.domain.entities.Category;
import jakarta.validation.constraints.NotEmpty;

public record CreateCategoryRequestDTO(

        @NotEmpty(message = "Name is required")
        String name
) {

   public static Category toDomain(CreateCategoryRequestDTO createCategoryRequestDTO) {
      return new Category(
         createCategoryRequestDTO.name()
      );
   }
}
