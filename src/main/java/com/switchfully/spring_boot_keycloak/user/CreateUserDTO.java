package com.switchfully.spring_boot_keycloak.user;

public record CreateUserDTO(String userName, String password, String role) {
}
