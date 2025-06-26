package com.isc.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.isc.exception.JwtAccessDeniedHandler;
import com.isc.exception.JwtAuthenticationEntryPoint;
import com.isc.security.JwtAuthorizationFilter;


@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
	JwtAuthorizationFilter authorizationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,JwtAuthenticationEntryPoint authenticationEntryPoint,JwtAccessDeniedHandler accessDeniedHandler) throws Exception {
		return http
				.csrf(csrf ->  csrf.disable())
				.cors(cors ->
                cors.configurationSource(corsConfigurationSource()))
				.exceptionHandling(ex->{
					ex.authenticationEntryPoint(authenticationEntryPoint);
					ex.accessDeniedHandler(accessDeniedHandler);
				}) 
				.authorizeHttpRequests(req -> {
					req.requestMatchers(
						    "/api/v1/docs",
						    "/api/v1/docs/**",
						    "/v3/api-docs",
						    "/v3/api-docs/**",
						    "/swagger-ui.html",
						    "/swagger-ui/**"
						).permitAll();
					req.anyRequest().authenticated();
				})
				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public SessionRegistry registry() {
		return new SessionRegistryImpl();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200", "https://isc-inventory-front.onrender.com"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
