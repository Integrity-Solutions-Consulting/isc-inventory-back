package com.isc.controllers;

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

import com.isc.dto.request.IdentificationTypeRequestDTO;
import com.isc.dto.request.NationalityRequestDTO;
import com.isc.dto.response.IdentificationTypeDetailResponseDTO;
import com.isc.dto.response.IdentificationTypeResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.NationalityDetailResponseDTO;
import com.isc.dto.response.NationalityResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.IdentificationTypeService;
import com.isc.service.NationalityService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/identification-type")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class IdentificationTypeController {
	private final IdentificationTypeService service;

    @GetMapping("/getAllDetails")
    public ResponseEntity<ResponseDto<List<IdentificationTypeDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(service.getAllDetails());
    }

    @GetMapping("/getSimpleList")
    public ResponseEntity<ResponseDto<List<IdentificationTypeResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(service.getSimpleList());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<IdentificationTypeDetailResponseDTO>> save(@RequestBody IdentificationTypeRequestDTO request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<IdentificationTypeDetailResponseDTO>> update(@RequestBody IdentificationTypeRequestDTO request,
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
