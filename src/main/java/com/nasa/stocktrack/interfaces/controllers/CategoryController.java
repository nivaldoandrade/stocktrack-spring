package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.category.*;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Category;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.category.CategoryDTO;
import com.nasa.stocktrack.interfaces.dtos.category.CreateCategoryRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.category.UpdateCategoryRequestDTO;
import com.nasa.stocktrack.interfaces.openapi.controllers.CategoryControllerOpenAPI;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryController implements CategoryControllerOpenAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ShowCategoryUseCase showCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponseDTO<CategoryDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {
        PaginatedList<Category> categoryPaginatedList = listCategoryUseCase.execute(
                page,
                size,
                OrderByEnum.fromString(orderBy),
                search
        );

        return ResponseEntity.ok(ListResponseDTO.toResponse(categoryPaginatedList, CategoryDTO::toResponse));
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> show(@ValidUUID @PathVariable String id) {

        Category category = showCategoryUseCase.execute(UUID.fromString(id));

        return ResponseEntity.ok(CategoryDTO.toResponse(category));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> create(@RequestBody @Validated CreateCategoryRequestDTO createCategoryRequestDTO) {
        Category category = createCategoryUseCase.execute(CreateCategoryRequestDTO.toDomain(createCategoryRequestDTO));

        URI uri = ResourceURIHelper.getURI(category.getId());

        return ResponseEntity.created(uri).body(CategoryDTO.toResponse(category));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @RequestBody @Validated UpdateCategoryRequestDTO updateCategoryRequestDTO
    ) {
        Category category = UpdateCategoryRequestDTO.toDomain(UUID.fromString(id), updateCategoryRequestDTO);

        updateCategoryUseCase.execute(category);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@ValidUUID @PathVariable String id) {
        deleteCategoryUseCase.execute(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }
}
