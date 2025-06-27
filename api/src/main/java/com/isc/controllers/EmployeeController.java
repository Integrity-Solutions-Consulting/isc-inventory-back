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

import com.isc.dto.request.EmployeeRequestDTO;
import com.isc.dto.response.EmployeeCatalogResponseDTO;
import com.isc.dto.response.EmployeeDetailResponseDTO;
import com.isc.dto.response.EmployeeTableResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {
	private final EmployeeService service;

	@GetMapping("/getTable")
	public ResponseEntity<ResponseDto<List<EmployeeTableResponseDTO>>> getAllTable() {
		return ResponseEntity.ok(service.getAllTable());
	}

	@GetMapping("/getSimpleList")
	public ResponseEntity<ResponseDto<List<EmployeeCatalogResponseDTO>>> getSimpleList() {
		return ResponseEntity.ok(service.getSimpleList());
	}
	
	@GetMapping("/getInfo/{id}")
	public ResponseEntity<ResponseDto<EmployeeDetailResponseDTO>> getInfoById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getInfoById(id));
	}

	@PostMapping("/save")
	public ResponseEntity<ResponseDto<EmployeeTableResponseDTO>> save(@Valid@RequestBody EmployeeRequestDTO request) {
		return ResponseEntity.ok(service.save(request));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<EmployeeTableResponseDTO>> update(@Valid@RequestBody EmployeeRequestDTO request,
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
