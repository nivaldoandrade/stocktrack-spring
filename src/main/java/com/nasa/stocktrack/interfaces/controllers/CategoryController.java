package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.category.CreateCategoryUseCase;
import com.nasa.stocktrack.application.usecases.category.ListCategoryUseCase;
import com.nasa.stocktrack.application.usecases.category.ShowCategoryUseCase;
import com.nasa.stocktrack.application.usecases.category.UpdateCategoryUseCase;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.entities.ListCategory;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.CategoryDTO;
import com.nasa.stocktrack.interfaces.dtos.CreateCategoryRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.ListCategoryResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.UpdateCategoryRequestDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ShowCategoryUseCase showCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    @GetMapping
    public ResponseEntity<ListCategoryResponseDTO> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {
        ListCategory listCategory = listCategoryUseCase.execute(page, size, OrderByEnum.fromString(orderBy), search);

        return ResponseEntity.ok(ListCategoryResponseDTO.toResponse(listCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> show(@ValidUUID @PathVariable String id) {

        Category category = showCategoryUseCase.execute(UUID.fromString(id));

        return ResponseEntity.ok(CategoryDTO.toResponse(category));
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> create(@RequestBody @Validated CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = createCategoryUseCase.execute(CreateCategoryRequestDTO.toDomain(createCategoryRequestDTO));

        URI uri = ResourceURIHelper.getURI(category.getId());

        return ResponseEntity.created(uri).body(CategoryDTO.toResponse(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @ValidUUID @PathVariable String id,
            @RequestBody @Validated UpdateCategoryRequestDTO updateCategoryRequestDTO
    ) {
        Category category = UpdateCategoryRequestDTO.toDomain(UUID.fromString(id), updateCategoryRequestDTO);

        updateCategoryUseCase.execute(category);

        return ResponseEntity.noContent().build();
    }
}
