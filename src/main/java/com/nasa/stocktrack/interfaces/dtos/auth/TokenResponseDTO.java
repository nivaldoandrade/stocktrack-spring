package com.nasa.stocktrack.interfaces.dtos.auth;

import lombok.Builder;

@Builder
public record TokenResponseDTO(
        String accessToken
) {

    public static TokenResponseDTO toResponse(String accessToken) {
        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
