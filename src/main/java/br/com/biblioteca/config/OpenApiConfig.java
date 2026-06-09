package br.com.biblioteca.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Biblioteca API",
                version = "1.0",
                description = "API para gerenciamento de livros e categorias",
                contact = @Contact(
                        name = "Equipe Biblioteca",
                        email = "grupo@biblioteca.com"
                ),
                license = @License(
                        name = "MIT"
                )
        )
)
public class OpenApiConfig {
}