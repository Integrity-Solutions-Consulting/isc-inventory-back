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

import com.isc.auth.dto.request.MenuRequestDTO;
import com.isc.auth.dto.request.PrivilegeRequestDTO;
import com.isc.auth.dto.response.MenuResponseDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.service.MenuService;
import com.isc.auth.service.PrivilegeService;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
	private final MenuService service;

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<MenuResponseDTO>>> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDto<MenuResponseDTO>> save(@RequestBody MenuRequestDTO request) {
		return ResponseEntity.ok(service.save(request));
	}
	

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<MenuResponseDTO>> update(@RequestBody MenuRequestDTO request, @PathVariable Integer id) {
		return ResponseEntity.ok(service.update(request,id));
	}

	@PutMapping("/activate/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) {
		return ResponseEntity.ok(service.active(id));
	}

	@DeleteMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> deleteUser(@PathVariable Integer id) {
		return ResponseEntity.ok(service.inactive(id));
	}
}
