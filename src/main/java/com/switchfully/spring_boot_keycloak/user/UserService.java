package com.switchfully.spring_boot_keycloak.user;

import com.switchfully.spring_boot_keycloak.security.KeycloakService;
import com.switchfully.spring_boot_keycloak.security.KeycloakUserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final KeycloakService keycloakService;

    public UserService(UserRepository userRepository, UserMapper userMapper, KeycloakService keycloakService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.keycloakService = keycloakService;
    }

    public void createUser(CreateUserDTO createUserDTO) {
        User savedUser = userRepository.save(userMapper.toUser(createUserDTO));
        keycloakService.addUser(new KeycloakUserDTO(savedUser.getUserName(), createUserDTO.password(), savedUser.getRole()));
    }
}
