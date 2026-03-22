package com.taskium.project.Infrastructure.Docs.Swagger;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.awt.*;
import java.net.URI;

@Profile("dev")
@Configuration
public class SwaggerConfig {

    @Bean
    public CommandLineRunner openSwagger() {
        return args -> {
            try {
//                Thread.sleep(1500);
                Runtime.getRuntime().exec("xdg-open http://localhost:8080/swagger-ui/index.html");
            } catch (Exception e) {
                System.out.println("Erro ao abrir Swagger");
            }
        };
    }
}
