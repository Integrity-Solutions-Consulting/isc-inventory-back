package com.isc.api.controllers;

import com.isc.api.dto.request.SupplierRequestDTO;
import com.isc.api.dto.response.SupplierDetailResponseDTO;
import com.isc.api.dto.response.SupplierResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventory/api/v1/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<SupplierDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(supplierService.getAllDetails());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<SupplierResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(supplierService.getSimpleList());
    }
    
    @GetMapping("/supplierType/{supplierTypeId}")
    public ResponseEntity<ResponseDto<List<SupplierResponseDTO>>> getSuppliersByType(
            @PathVariable Integer supplierTypeId) {
        ResponseDto<List<SupplierResponseDTO>> response = supplierService.getBySupplierType(supplierTypeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<SupplierDetailResponseDTO>> create(
            @Valid @RequestBody SupplierRequestDTO request) {
        return ResponseEntity.ok(supplierService.save(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<SupplierDetailResponseDTO>> update(
            @Valid @RequestBody SupplierRequestDTO request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(supplierService.update(request, id));
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(supplierService.inactive(id));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(supplierService.active(id));
    }
}
