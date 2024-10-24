package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.user.*;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.user.CreateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UpdateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UserDTO;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ShowUserUseCase showUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @GetMapping
    public ResponseEntity<ListResponseDTO<UserDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    ) {

        PaginatedList<User> userPaginatedList = listUserUseCase.execute(
                page,
                size,
                OrderByEnum.fromString(orderBy),
                search
        );

        return ResponseEntity.ok(ListResponseDTO.toResponse(userPaginatedList, UserDTO::toResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> show(@ValidUUID @PathVariable String id) {

        User user = showUserUseCase.execute(UUID.fromString(id));


        return ResponseEntity.ok(UserDTO.toResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Validated @RequestBody CreateUserRequestDTO createUserRequestDTO) {

        User user = createUserUseCase.execute(createUserRequestDTO.toDomain());

        URI uri = ResourceURIHelper.getURI(user.getId());

        return ResponseEntity.created(uri).body(UserDTO.toResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @Validated @RequestBody UpdateUserRequestDTO updateUserRequestDTO
    ) {

        updateUserUseCase.execute(updateUserRequestDTO.toDomain(UUID.fromString(id)));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@ValidUUID @PathVariable String id) {

        deleteUserUseCase.execute(UUID.fromString(id));

        return ResponseEntity.noContent().build();
    }
}
