package com.backend.laboratorio;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LaboratorioApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioApplication.class, args);


	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(new Components()
						.addSecuritySchemes("bearer-key",
								new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
				.info(new Info().title("Laboratorio API")
						.description("API REST gestión de resultados de experimentos")
						.contact(new Contact()
								.name("Pau Macià")
								.email("protactiniou@gmail.com")
								.url("https://github.com/paums98"))
						.version("1.0"))
				.addSecurityItem(new SecurityRequirement().addList("bearer-key"));
	}

}


