package com.isc.controllers;

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

import com.isc.dto.request.NationalityRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.NationalityDetailResponseDTO;
import com.isc.dto.response.NationalityResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.NationalityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/nationality")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class NationalityController {
	private final NationalityService service;

	@GetMapping("/getAllDetails")
	public ResponseEntity<ResponseDto<List<NationalityDetailResponseDTO>>> getAllDetails() {
		return ResponseEntity.ok(service.getAllDetails());
	}

	@GetMapping("/getSimpleList")
	public ResponseEntity<ResponseDto<List<NationalityResponseDTO>>> getSimpleList() {
		return ResponseEntity.ok(service.getSimpleList());
	}

	@PostMapping("/save")
	public ResponseEntity<ResponseDto<NationalityDetailResponseDTO>> save(@Valid@RequestBody NationalityRequestDTO request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<NationalityDetailResponseDTO>> update(@Valid@RequestBody NationalityRequestDTO request,
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
