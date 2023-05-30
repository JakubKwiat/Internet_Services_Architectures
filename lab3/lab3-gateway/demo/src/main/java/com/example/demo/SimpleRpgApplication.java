package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Application main class.
 */
@SpringBootApplication
public class SimpleRpgApplication {

	/**
	 * Application main entry point. Starts Spring Boot application context.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SimpleRpgApplication.class, args);
	}

	/**
	 * Generally the {@link Bean} annotated methods are producer methods returning Spring configuration beans. This
	 * method registers Gateway routing rules.
	 *
	 * @param builder provided route locator builder
	 * @return route locator with defined routes
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder
				.routes()
				.route("footballteams", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/footballteams/{name}", "/api/footballteams")
						.uri("http://localhost:8081"))
				.route("players", r -> r
						.host("localhost:8080")
						.and()
						.path("/api/players", "/api/players/**", "/api/footballteams/{name}/players", "/api/footballteams/{name}/players/**")
						.uri("http://localhost:8082"))
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
