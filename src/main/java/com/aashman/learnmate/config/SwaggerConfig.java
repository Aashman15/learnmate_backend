package com.aashman.learnmate.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configure() {
        return new OpenAPI().info(new Info().title("Learnmate api").description("Api for learnmate clients").contact(new Contact().name("Aashman Thing").email("aashmanthing2@gmail.com")));
    }

}