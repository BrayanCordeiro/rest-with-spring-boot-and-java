package com.brayancordeiro.restwithspringboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API with java 21 and Spring Boot")
						.version("v1")
						.description("Aqui viria uma descrição a respeito da API")
						.termsOfService("https://github.com/BrayanCordeiro/rest-with-spring-boot-and-java")
						.license(
								new License()
								.name("Apache 2.0")
								.url("https://github.com/BrayanCordeiro/rest-with-spring-boot-and-java")));
	}

}
