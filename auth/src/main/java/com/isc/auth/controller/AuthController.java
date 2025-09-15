package com.isc.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.auth.dto.request.LoginRequestDTO;
import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.TokenResponseDTO;
import com.isc.auth.dto.response.UserRegisterResponseDTO;
import com.isc.auth.service.AuthService;
import com.isc.dtos.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService service;

	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<UserRegisterResponseDTO>> register(@Valid @RequestBody UserRequestoDTO request) {
		return ResponseEntity.ok(service.register(request));
	}

	@Operation(summary = "Login to obtain JWT token")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Login successful"),
			@ApiResponse(responseCode = "401", description = "Invalid credentials") })
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequestDTO request) {
		return ResponseEntity.ok("This is a Swagger doc placeholder."); // Este método nunca será ejecutado, solo es para documentar
	}
	
	@GetMapping("/forgotPassword/request/{email}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> generateTokenForgotPassword(@PathVariable String email) {
		return ResponseEntity.ok(service.generateTokenForgotPassword(email));
	}
	
	@GetMapping("/forgotPassword/validateToken/{token}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> validateTokenForgotPassword(@PathVariable String token) {
		return ResponseEntity.ok(service.validateTokenForgotPassword(token));
	}
	
	@PostMapping("/forgotPassword/restorePassword/{token}")
	public ResponseEntity<ResponseDto<Boolean>> restorePassword(@PathVariable String token, @Valid @RequestBody PasswordChangeRequestDTO request) {
		return ResponseEntity.ok(service.restorePassword(token, request));
	}
	
	@Operation(summary = "Logout the current user", description = "Invalidates the user's session or token")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Logout successful"),
	    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid or missing token")
	})
	@PostMapping("/logout")
	public ResponseEntity<String> logout() {
	    return ResponseEntity.ok("This is a Swagger doc placeholder."); // Este método nunca se ejecuta
	}
	

}
