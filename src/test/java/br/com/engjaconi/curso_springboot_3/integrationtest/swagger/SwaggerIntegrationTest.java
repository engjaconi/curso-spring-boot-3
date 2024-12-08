package br.com.engjaconi.curso_springboot_3.integrationtest.swagger;

import static io.restassured.RestAssured.given;

import br.com.engjaconi.curso_springboot_3.config.TestConfig;
import br.com.engjaconi.curso_springboot_3.integrationtest.AbstractIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    void shouldDisplaySwaggerUiPage() {
        String content = given()
                .basePath("/swagger-ui/index.html")
                .port(TestConfig.SERVER_PORT)
                .when()
                    .get()
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                        .asString();

        Assertions.assertTrue(content.contains("Swagger UI"));
    }
}
