package com.isc.auth.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.auth.dto.request.ApplicationRequestDTO;
import com.isc.auth.dto.response.ApplicationResponseDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.service.ApplicationService;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/application")
@RequiredArgsConstructor
public class ApplicationController {

	private final ApplicationService service;

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<ApplicationResponseDTO>>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}

	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<ApplicationResponseDTO>> save(@RequestBody ApplicationRequestDTO request) {
		return ResponseEntity.ok(service.save(request));
	}
	
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<ApplicationResponseDTO>> update(@RequestBody ApplicationRequestDTO request, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(request,id));
	}


	@PutMapping("/activate/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) {
		return ResponseEntity.ok(service.active(id));
	}

	@DeleteMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
		return ResponseEntity.ok(service.inactive(id));
	}
}
