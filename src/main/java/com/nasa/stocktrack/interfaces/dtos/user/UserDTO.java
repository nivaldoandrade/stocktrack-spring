package com.nasa.stocktrack.interfaces.dtos.user;

import com.nasa.stocktrack.domain.entities.User;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UserDTO(
        UUID id,

        String fullName,

        String username
) {
    public static UserDTO toResponse(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFull_name())
                .username(user.getUsername())
                .build();
    }
}
