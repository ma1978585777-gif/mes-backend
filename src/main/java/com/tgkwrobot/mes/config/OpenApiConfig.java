package com.tgkwrobot.mes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("天工MES API")
                        .contact(new Contact().name("马祥").email("maxiang@tgkwrobot.com"))
                        .version("1.0")
                        .description("天工MES API"));
    }
}