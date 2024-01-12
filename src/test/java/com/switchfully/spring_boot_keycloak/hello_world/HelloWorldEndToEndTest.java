package com.switchfully.spring_boot_keycloak.hello_world;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class HelloWorldEndToEndTest {

    @LocalServerPort
    private Long port;


    @Test
    void name() {
        String result = RestAssured.given()
                .baseUri("http://localhost:" + port)
                .when()
                .get("public-hello-world")
                .then()
                .extract()
                .as(String.class);

        Assertions.assertThat(result).isEqualTo("");
    }
}
