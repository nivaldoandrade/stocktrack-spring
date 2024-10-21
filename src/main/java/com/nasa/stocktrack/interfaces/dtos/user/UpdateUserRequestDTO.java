package com.nasa.stocktrack.interfaces.dtos.user;

import com.nasa.stocktrack.domain.entities.User;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateUserRequestDTO(

        @NotBlank(message = "FullName is required")
        String fullName,

        @NotBlank(message = "Username is required")
        String username,

        String password
) {

    public User toDomain(UUID id) {
        return new User(
                id,
                fullName,
                username,
                password
        );
    }
}
