package com.isc.api.controllers;

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

import com.isc.api.dto.request.GenderRequestDTO;
import com.isc.api.dto.response.GenderDetailResponseDTO;
import com.isc.api.dto.response.GenderResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.GenderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/gender")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
public class GenderController {
	private final GenderService service;
	
	@GetMapping("/getAllDetails")
	public ResponseEntity<ResponseDto<List<GenderDetailResponseDTO>>> getAllDetails(){
		return ResponseEntity.ok(service.getAllDetails());
	}
	
	@GetMapping("/getSimpleList")
	public ResponseEntity<ResponseDto<List<GenderResponseDTO>>> getSimpleList(){
		return ResponseEntity.ok(service.getSimpleList());
	}
	
	@PostMapping("/save")
	public ResponseEntity<ResponseDto<GenderDetailResponseDTO>> save(@Valid@RequestBody GenderRequestDTO request){
		return ResponseEntity.ok(service.save(request));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDto<GenderDetailResponseDTO>> update(@Valid@RequestBody GenderRequestDTO request, @PathVariable Integer id){
		return ResponseEntity.ok(service.update(request,id));
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
