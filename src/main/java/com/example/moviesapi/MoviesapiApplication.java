package com.example.moviesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "NTT API-Movies", version = "1", description = "API for NTT"))
public class MoviesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesapiApplication.class, args);
	}
}
