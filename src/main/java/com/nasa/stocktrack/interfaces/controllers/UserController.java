package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.user.*;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.user.CreateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UpdateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UserDTO;
import com.nasa.stocktrack.interfaces.openapi.controllers.UserControllerOpenAPI;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController implements UserControllerOpenAPI {

    private final CreateUserUseCase createUserUseCase;
    private final ShowUserUseCase showUserUseCase;
    private final ListUserUseCase listUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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

    @Override
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> me(Authentication authentication) {
        UserEntity userAuth = (UserEntity) authentication.getPrincipal();
        UUID userAuthId = userAuth.getId();

        User user = showUserUseCase.execute(userAuthId);

        return ResponseEntity.ok(UserDTO.toResponse(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id.toString()")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> show(@ValidUUID @PathVariable String id) {

        User user = showUserUseCase.execute(UUID.fromString(id));


        return ResponseEntity.ok(UserDTO.toResponse(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> create(@Validated @RequestBody CreateUserRequestDTO createUserRequestDTO) {

        User user = createUserUseCase.execute(createUserRequestDTO.toDomain());

        URI uri = ResourceURIHelper.getURI(user.getId());

        return ResponseEntity.created(uri).body(UserDTO.toResponse(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id.toString()")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @Validated @RequestBody UpdateUserRequestDTO updateUserRequestDTO
    ) {
        updateUserUseCase.execute(updateUserRequestDTO.toDomain(UUID.fromString(id)));

        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(
            @ValidUUID @PathVariable String id,
            Authentication authentication
    ) {

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        UUID userAuthId = userEntity.getId();

        deleteUserUseCase.execute(UUID.fromString(id), userAuthId);

        return ResponseEntity.noContent().build();
    }
}
