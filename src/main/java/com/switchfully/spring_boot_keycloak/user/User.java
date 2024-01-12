package com.switchfully.spring_boot_keycloak.user;

import com.switchfully.spring_boot_keycloak.security.Role;

public class User {
    private final String userName;
    private final Role role;

    public User(String userName, Role role) {
        this.userName = userName;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public Role getRole() {
        return role;
    }
}
