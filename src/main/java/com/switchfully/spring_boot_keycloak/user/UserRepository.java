package com.switchfully.spring_boot_keycloak.user;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    private final List<User> userList = new ArrayList<>();

    public User save(User user) {
        userList.add(user);
        return user;
    }
}
