package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;
import com.isc.api.dto.request.WorkModeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.WorkModeDetailResponseDTO;
import com.isc.api.dto.response.WorkModeResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.WorkModeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/work-mode")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class WorkModeController {

	private final WorkModeService service;

	@GetMapping("/getAllDetails")
	public ResponseEntity<ResponseDto<List<WorkModeDetailResponseDTO>>> getAllDetails() {
		return ResponseEntity.ok(service.getAllDetails());
	}

	@GetMapping("/getSimpleList")
	public ResponseEntity<ResponseDto<List<WorkModeResponseDTO>>> getSimpleList() {
		return ResponseEntity.ok(service.getSimpleList());
	}

	@PostMapping("/save")
	public ResponseEntity<ResponseDto<WorkModeDetailResponseDTO>> save(@Valid @RequestBody WorkModeRequestDTO request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<WorkModeDetailResponseDTO>> update(@Valid @RequestBody WorkModeRequestDTO request,
			@PathVariable Integer id) {
		return ResponseEntity.ok(service.update(request, id));
	}

	@PutMapping("/activate/{id}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) {
		return ResponseEntity.ok(service.active(id));
	}

	@DeleteMapping("/inactive/{id}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
		return ResponseEntity.ok(service.inactive(id));
	}
}
