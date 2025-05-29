package com.isc.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.dto.request.PositionRequestDTO;
import com.isc.dto.request.WorkModeRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.PositionDetailResponseDTO;
import com.isc.dto.response.PositionResponseDTO;
import com.isc.dto.response.WorkModeDetailResponseDTO;
import com.isc.dto.response.WorkModeResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.PositionService;
import com.isc.service.WorkModeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/work-mode")
@RequiredArgsConstructor
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
	public ResponseEntity<ResponseDto<WorkModeDetailResponseDTO>> save(@RequestBody WorkModeRequestDTO request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<WorkModeDetailResponseDTO>> update(@RequestBody WorkModeRequestDTO request,
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
