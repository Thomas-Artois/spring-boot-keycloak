package com.switchfully.spring_boot_keycloak.security;

public record KeycloakUserDTO (String userName, String password, Role role){
}
