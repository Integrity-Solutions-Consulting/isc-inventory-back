package com.isc.controllers;

import com.isc.dto.request.SupplierRequestDTO;
import com.isc.dto.response.SupplierDetailResponseDTO;
import com.isc.dto.response.SupplierResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<SupplierDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(supplierService.getAllDetails());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<SupplierResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(supplierService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<SupplierDetailResponseDTO>> create(
            @Valid @RequestBody SupplierRequestDTO request) {
        return ResponseEntity.ok(supplierService.save(request));
    }

    @PutMapping("/{id}")
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
