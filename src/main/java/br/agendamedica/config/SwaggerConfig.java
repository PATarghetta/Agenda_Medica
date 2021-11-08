package br.agendamedica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI examesOpenAPI() {
		return new OpenAPI().info(new Info().title("Exames Medicos API")
				.description(
						"API criada para o trabalho final da P2 de Desenvolvimentos de Servidores II")
				.version("v0.0.1")
				.contact(new Contact().name("Rosangela Borges").email("rosangela.borges@fatec.sp.gov.br"))
				.license(new License().name("Apache 2.0").url("http://springdoc.org/%22")));
	}
}