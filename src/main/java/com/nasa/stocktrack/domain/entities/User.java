package com.nasa.stocktrack.domain.entities;

import java.util.UUID;

public class User {

    private UUID id;

    private String full_name;

    private String username;

    private String password;

    private Role role;

    public User(UUID id, String full_name, String username, String password, Role role) {
        this.id = id;
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String full_name, String username, String password, Role role) {
        this.full_name = full_name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
