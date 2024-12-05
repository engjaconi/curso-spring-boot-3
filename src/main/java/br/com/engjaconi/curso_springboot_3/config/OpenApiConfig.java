package br.com.engjaconi.curso_springboot_3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                            .title("Titulo da API")
                            .version("v1")
                            .description("Descrição da API")
                            .termsOfService("Termos de serviço")
                            .license(new License()
                                    .name("Apache 2.0")
                                    .url("")
                            )
                );
    }

}
