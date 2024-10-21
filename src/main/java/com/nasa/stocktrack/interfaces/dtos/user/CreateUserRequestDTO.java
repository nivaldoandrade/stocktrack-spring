package com.nasa.stocktrack.interfaces.dtos.user;

import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.infra.constraints.ValidPasswordPattern;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequestDTO(

        @NotBlank(message = "FullName is required")
        String fullName,

        @NotBlank(message = "Username is required")
        String username,

        @ValidPasswordPattern(min = 6)
        String password
) {

    public User toDomain() {
        return new User(
                this.fullName,
                this.username,
                this.password
        );
    }
}
