package com.nasa.stocktrack.interfaces.dtos.auth;

import com.nasa.stocktrack.domain.dtos.SignInDTO;
import jakarta.validation.constraints.NotBlank;

public record SignInRequestDTO(

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password
) {

    public SignInDTO toDomain() {
        return new SignInDTO(username, password);
    }
}
