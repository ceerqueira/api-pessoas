package br.com.attornatus.Pessoas.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API para cadastrar pessoas")
                        .description(
                                "API Rest para cadastrar pessoas, contendo as funcionalidades de CRUD de dados das pesssoas como nome e data de nascimento e endereços")
                        .version("v1.0.0")
                        .license(new License().name("Apache 4.0").url("http://springdoc.org"))
                        );
    }
}