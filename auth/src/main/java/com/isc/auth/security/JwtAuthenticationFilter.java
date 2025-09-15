package com.isc.auth.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isc.auth.dto.response.UserLoginResponseDTO;
import com.isc.auth.entitys.UserEntity;
import com.isc.auth.mapper.UserMapper;
import com.isc.auth.repository.UserRepository;
import com.isc.auth.service.UserService;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private JwtService jwtService;

	private UserService userService;

	private ObjectMapper objectMapper;

	public JwtAuthenticationFilter(JwtService jwtService, UserService userService, ObjectMapper objectMapper) {
		this.jwtService = jwtService;
		this.userService = userService;
		this.objectMapper = objectMapper;
		setFilterProcessesUrl("/api/v1/auth/login"); // Endpoint for authentication
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UserEntity userEntity = null;
		String email = "";
		String password = "";
		try {
			userEntity = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
			email = userEntity.getEmail();
			password = userEntity.getPassword();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
				password);
		try {
			return getAuthenticationManager().authenticate(authenticationToken);
		} catch (AuthenticationException e) {
			throw e;
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		User user = (User) authResult.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		String email = user.getUsername();
		UserLoginResponseDTO dto =this.userService.processLogin(email, authorities);

		// Genera el token
		String token = jwtService.generateToken(email, authorities);

		dto.setToken(token);
		response.addHeader("Authorization", token);
		MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Login satisfactorio");
		ResponseDto<UserLoginResponseDTO> responseDTO = new ResponseDto<>(dto, metadata);
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().write(objectMapper.writeValueAsString(responseDTO));
		response.getWriter().flush();
	}
}
/*
 * response.setStatus(HttpStatus.UNAUTHORIZED.value());
 * response.setContentType(MediaType.APPLICATION_JSON_VALUE);
 * response.getWriter().write("{\"error\": \"User not found\"}");
 * response.getWriter().flush();
 */