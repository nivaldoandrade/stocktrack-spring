package com.nasa.stocktrack.interfaces.dtos.user;

import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.enums.RoleEnum;
import com.nasa.stocktrack.infra.constraints.EnumRolePattern;
import com.nasa.stocktrack.infra.constraints.ValidPasswordPattern;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


import java.util.UUID;

public record UpdateUserRequestDTO(

        @NotBlank(message = "FullName is required")
        String fullName,

        @NotBlank(message = "Username is required")
        String username,

        @ValidPasswordPattern(min = 6, required = false)
        String password,

        @Schema(example = "USER or ADMIN", implementation = RoleEnum.class)
        @EnumRolePattern
        String role
) {

    public User toDomain(UUID id) {
        RoleEnum roleEnum = RoleEnum.fromString(role);
        Role role = new Role(roleEnum);

        return new User(
                id,
                fullName,
                username,
                password,
                role
        );
    }
}
