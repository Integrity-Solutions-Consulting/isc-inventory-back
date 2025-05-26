package com.isc.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/passwordEncoder")
@RequiredArgsConstructor
public class UtilsController {
	
	private final PasswordEncoder encoder;

	@GetMapping("/{password}")
	public ResponseEntity<String> getPasswordEncoder(@PathVariable String password){
		return ResponseEntity.ok(encoder.encode(password));
	}
}
