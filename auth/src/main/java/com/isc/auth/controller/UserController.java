package com.isc.auth.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.auth.dto.request.PasswordChangeRequestDTO;
import com.isc.auth.dto.request.UserRequestoDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.auth.dto.response.UserDetailsResponseDTO;
import com.isc.auth.dto.response.UserResponseDTO;
import com.isc.auth.service.UserService;
import com.isc.dtos.ResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<List<UserResponseDTO>>> getAll(){
		return ResponseEntity.ok(service.getAllUserDTOs());
	}
	
	@GetMapping("/detail/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<UserDetailsResponseDTO>> getById(@PathVariable Integer id){
		return ResponseEntity.ok(service.getDetailsById(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<UserResponseDTO>> updateUser(@Valid@RequestBody UserRequestoDTO request, @PathVariable Integer id){
		return ResponseEntity.ok(service.updateUser(request, id));
	}
	
	@PutMapping("/changePassword/{id}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> changePassword(@Valid @RequestBody PasswordChangeRequestDTO request, @PathVariable Integer id){
		return ResponseEntity.ok(service.changePassword(request, id));
	}
	
	@PutMapping("/activate/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> activateUser(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.activeUser(id));
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> deleteUser(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.deleteUser(id));
	}

	@PutMapping("/suspend/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> suspendUser(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.suspendUser(id));
	}

	@PutMapping("/unsuspend/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> unsuspendUser(@PathVariable Integer id) {
	    return ResponseEntity.ok(service.unsuspendUser(id));
	}
}
