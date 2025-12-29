package com.isc.auth.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isc.auth.exception.JwtAccessDeniedHandler;
import com.isc.auth.exception.JwtAuthenticationEntryPoint;
import com.isc.auth.repository.UserRepository;
import com.isc.auth.security.JwtAuthenticationFilter;
import com.isc.auth.security.JwtAuthorizationFilter;
import com.isc.auth.security.JwtService;
import com.isc.auth.security.LogoutCustomHandler;
import com.isc.auth.service.UserService;
import com.isc.auth.service.impl.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	JwtService jwtService;

	@Autowired
	UserService userService;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	JwtAuthorizationFilter authorizationFilter;

	@Autowired
	LogoutCustomHandler logoutHandler;

	@Autowired
	ObjectMapper objectMapper;
	
	@Value("${frontend.url}")
	private String serverUrl;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager,
			JwtAuthenticationEntryPoint authenticationEntryPoint, JwtAccessDeniedHandler accessDeniedHandler)
			throws Exception {
		JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(jwtService, userService,
				objectMapper);
		authenticationFilter.setAuthenticationManager(authenticationManager);
		return http.csrf(csrf -> csrf.disable()).cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.exceptionHandling(ex -> {
					ex.authenticationEntryPoint(authenticationEntryPoint);
					ex.accessDeniedHandler(accessDeniedHandler);
				}).authorizeHttpRequests(req -> {
					
					req.requestMatchers("/api/v1/auth/login",
							"/api/v1/auth/forgotPassword/**",
							"/api/v1/passwordEncoder/**",
							"/api/v1/docs", "/api/v1/docs/**",
							"/v3/api-docs",
							"/v3/api-docs/**",
							"/swagger-ui.html",
							"/swagger-ui/**").permitAll();
					//req.anyRequest().authenticated();
				}).sessionManagement(session -> {
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
					session.maximumSessions(1).sessionRegistry(registry());
				}).addFilter(authenticationFilter)
				.addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.logout(logout -> logout.logoutUrl("/api/v1/auth/logout").addLogoutHandler(logoutHandler)
						.logoutSuccessHandler(
								(request, response, authentication) -> SecurityContextHolder.clearContext()))
				.build();
	}

	@Bean
	public SessionRegistry registry() {
		return new SessionRegistryImpl();
	}

	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder encoder) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsServiceImpl).passwordEncoder(encoder).and().build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:4200", serverUrl,"https://api.timereport.integritysolutions.com.ec","https://app.timereport.integritysolutions.com.ec", "http://147.93.181.146:7070"));
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
