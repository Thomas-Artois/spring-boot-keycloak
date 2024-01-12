package com.switchfully.spring_boot_keycloak.hello_world;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("disable-keycloak")
class HelloWorldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "Test", authorities = "GET_CUSTOMER_HELLO_WORLD")
    void canAccessEndpoint() throws Exception {
        mockMvc.perform(get("/customer-hello-world")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Test", authorities = "GET_CUSTOMER_HELLO_WORLD")
    void canNotAccessEndpoint() throws Exception {
        mockMvc.perform(get("/admin-hello-world")).andExpect(status().isForbidden());
    }

    @Test
    @WithAnonymousUser
    void noLogin() throws Exception {
        mockMvc.perform(get("/admin-hello-world")).andExpect(status().isUnauthorized());
    }
}
