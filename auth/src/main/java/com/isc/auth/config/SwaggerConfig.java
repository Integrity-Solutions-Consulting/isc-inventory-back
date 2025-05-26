package com.isc.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
		info=@Info(
				title="Inventory AUTH API - JWT Secured",
				description = "This API supports user creation, update, deletion, and retrieval operations, with security based on JWT authentication to protect access to resources, using PostgreSQL database",
				termsOfService = "This is open-access API",
				contact= @Contact(
						name="Adrian Siavichay",
						email="adrian2x2001@gmail.com")),
		security = @SecurityRequirement(
				name = "Security Token")
		)
@SecurityScheme(
		name="Security Token",
		description = "Acces Token for my API",
		type = SecuritySchemeType.HTTP,
		paramName = HttpHeaders.AUTHORIZATION,
		in = SecuritySchemeIn.HEADER,
		scheme = "bearer",
		bearerFormat = "JWT")
public class SwaggerConfig {

}
