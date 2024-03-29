package com.microservice.gateway.config;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
public class WebConfig {

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		.authorizeExchange(auth -> auth
				.pathMatchers("/api/**").permitAll()
				.anyExchange().authenticated())
		.cors(Customizer.withDefaults())
		;

		return http.build();
	}

}