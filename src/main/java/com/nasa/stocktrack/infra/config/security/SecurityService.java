package com.nasa.stocktrack.infra.config.security;

import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
    private final HttpServletRequest request;

    public SecurityService(HttpServletRequest request) {
        this.request = request;
    }

    public String getUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserEntity user)) {
            return null;
        }

        UUID id = user.getId();

        return id.toString();
    }

    public boolean isAuthenticated() {
        return getUserId() != null && !getUserId().isEmpty();
    }

    public boolean isNonAuthenticated() {
        return !isAuthenticated() && !isSwaggerRequest();
    }

    private boolean isSwaggerRequest() {
        String uri = request.getRequestURI();
        return uri != null && (uri.startsWith("/swagger-ui") || uri.startsWith("/v3/api-docs"));
    }
}