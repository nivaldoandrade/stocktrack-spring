package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.category.CreateCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.CategoryDTO;
import com.nasa.stocktrack.interfaces.dtos.CreateCategoryRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@RequestBody @Validated CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = createCategoryUseCase.execute(CreateCategoryRequestDTO.toDomain(createCategoryRequestDTO));

        URI uri = ResourceURIHelper.getURI(category.getId());

        return ResponseEntity.created(uri).body(CategoryDTO.toResponse(category));
    }
}
