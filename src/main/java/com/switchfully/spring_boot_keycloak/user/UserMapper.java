package com.switchfully.spring_boot_keycloak.user;

import com.switchfully.spring_boot_keycloak.security.Role;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(CreateUserDTO createUserDTO) {
        return new User(createUserDTO.userName(), Role.valueOf(createUserDTO.role().toUpperCase()));
    }
}
