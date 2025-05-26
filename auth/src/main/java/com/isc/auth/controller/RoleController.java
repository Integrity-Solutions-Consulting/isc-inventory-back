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

import com.isc.auth.dto.request.RoleRequestDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.RolesResponseDTO;
import com.isc.auth.service.RoleService;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
	private final RoleService service;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<RolesResponseDTO>>> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	
	@PostMapping("/save")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<RolesResponseDTO>> save(@RequestBody RoleRequestDTO request){
		return ResponseEntity.ok(service.save(request));
	}
	
	@PutMapping("/addPrivileges/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<RolesResponseDTO>> addPrivileges(@RequestBody RoleRequestDTO request, @PathVariable Integer id){
		return ResponseEntity.ok(service.addPrivileges(request,id));
	}
	
	@PutMapping("/activate/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.activeRole(id));
	}

	@DeleteMapping("/inactive/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> deleteUser(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.inactiveRole(id));
	}
}
