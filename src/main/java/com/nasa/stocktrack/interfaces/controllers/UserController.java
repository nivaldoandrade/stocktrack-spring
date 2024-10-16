package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.user.CreateUserUseCase;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.interfaces.ResourceURIHelper;
import com.nasa.stocktrack.interfaces.dtos.user.CreateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping
    public ResponseEntity<UserDTO> create(@Validated @RequestBody CreateUserRequestDTO createUserRequestDTO) {

        User user = createUserUseCase.execute(createUserRequestDTO.toDomain());

        URI uri = ResourceURIHelper.getURI(user.getId());

        return ResponseEntity.created(uri).body(UserDTO.toResponse(user));
    }
}
