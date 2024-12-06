package com.nasa.stocktrack.infra.config.security;

import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityService {
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
        return !isAuthenticated();
    }

}