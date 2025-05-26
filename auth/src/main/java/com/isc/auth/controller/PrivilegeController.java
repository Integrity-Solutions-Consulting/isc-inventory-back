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

import com.isc.auth.dto.request.PrivilegeRequestDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.service.PrivilegeService;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/privilege")
@RequiredArgsConstructor
public class PrivilegeController {
	private final PrivilegeService service;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<PrivilegeResponseDTO>>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDto<PrivilegeResponseDTO>> updateUser(@RequestBody PrivilegeRequestDTO request){
		return ResponseEntity.ok(service.save(request));
	}
	
	@PutMapping("/activate/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.activePrivilege(id));
	}

	@DeleteMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> deleteUser(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.inactivePrivilege(id));
	}
}
