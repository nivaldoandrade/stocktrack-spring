package com.nasa.stocktrack.infra.config.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {


    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setName("Nivaldo Andrade");
        contact.setEmail("nivaldoandradef@gmail.com");
        contact.setUrl("https://nivaldoandrade.dev.br");

        Info info = new Info()
                .title("Stock Track API")
                .version("1.0")
                .contact(contact);

        String securitySchemaName = "Authorization";

        return new OpenAPI()
                .info(info)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemaName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemaName, new SecurityScheme()
                                .name(securitySchemaName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
