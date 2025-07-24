package com.isc.api.controllers;

import com.isc.api.service.SupplierTypeService;
import com.isc.dtos.ResponseDto;

import lombok.RequiredArgsConstructor;

import com.isc.api.dto.response.SupplierTypeResponseDTO;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/supplier-types")
public class SupplierTypeController 
{
    private final SupplierTypeService supplierTypeService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<SupplierTypeResponseDTO>>> getAllActive() {
        return ResponseEntity.ok(supplierTypeService.getAllActive());
    }
    
    @GetMapping("/idProveedor/{id}")
    public ResponseDto<SupplierTypeResponseDTO> getById(@PathVariable Integer id) {
        return supplierTypeService.getById(id);
    }
}