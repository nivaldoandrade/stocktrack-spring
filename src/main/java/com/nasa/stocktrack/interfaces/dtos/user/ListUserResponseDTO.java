package com.nasa.stocktrack.interfaces.dtos.user;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import lombok.Builder;

import java.util.List;

@Builder
public record ListUserResponseDTO(
        List<UserDTO> users,

        Long totalItems,

        Integer totalPages
) {

    public static ListUserResponseDTO toListResponse(PaginatedList<User> userPaginatedList) {
        List<UserDTO> users = userPaginatedList.getItems().stream().map(UserDTO::toResponse).toList();

        return ListUserResponseDTO.builder()
                .users(users)
                .totalItems(userPaginatedList.getTotalItems())
                .totalPages(userPaginatedList.getTotalPages())
                .build();
    }

}
