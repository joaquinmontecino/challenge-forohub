package com.alura.forohub.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("ForoHub - Challenge ONE G6")
                        .description("API REST del Foro Alura. " +
                                "\n\nLos siguientes endpoints permiten la gestión de Usuarios, Cursos, Topicos y sus Respuestas. " +
                                "\n\nPara interactuar con cada opción el usuario se debe autenticar con el token JWT entregado al iniciar sesión")
                        .version("1.1"))
                .components(new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme("bearer").bearerFormat("JWT")));
    }
}
