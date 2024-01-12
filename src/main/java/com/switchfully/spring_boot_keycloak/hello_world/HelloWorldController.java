package com.switchfully.spring_boot_keycloak.hello_world;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloWorldController {

    @GetMapping(path = "public-hello-world")
    public Message publicHelloWorld() {
        return new Message("public hello world");
    }

    @GetMapping(path = "customer-hello-world")
    @PreAuthorize("hasAuthority('GET_CUSTOMER_HELLO_WORLD')")
    public Message customerHelloWorld() {
        return new Message("customer hello world");
    }

    @GetMapping(path = "admin-hello-world")
    @PreAuthorize("hasAuthority('GET_ADMIN_HELLO_WORLD')")
    public Message adminHelloWorld() {
        return new Message("admin hello world");
    }

}
