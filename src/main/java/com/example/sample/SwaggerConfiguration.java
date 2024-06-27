package com.example.sample;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SwaggerConfiguration {

    private final Environment environment;

    public SwaggerConfiguration(final Environment environment) {
        this.environment = environment;
    }

    @Bean
    public OpenAPI setSwaggerConfiguration() {
        final String version = environment.getProperty("BUILD_VERSION");
        final String securitySchemeName = "basicAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("")
                        .description("")
                        .version(version != null ? version : "Unable to find version"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .addServersItem(new Server().url("/").description("Current server URL"))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("basic")));
    }
}
