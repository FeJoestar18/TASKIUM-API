package com.taskium.project.Infrastructure.Docs.Swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "Bearer Token";

        return new OpenAPI()
                .info(new Info()
                        .title("Taskium API")
                        .version("1.0")
                        .description("API do Taskium com autenticação JWT"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Insira o token JWT. Exemplo: eyJhbGciOi...")
                        ));
    }

    @Profile("dev")
    @Bean
    public CommandLineRunner openSwagger() {
        return args -> {
            try {
                Runtime.getRuntime().exec("xdg-open http://localhost:8080/swagger-ui/index.html");
            } catch (Exception e) {
                System.out.println("Erro ao abrir Swagger");
            }
        };
    }
}
