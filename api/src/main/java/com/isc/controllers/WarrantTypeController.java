package com.isc.controllers;

import com.isc.dto.request.WarrantTypeRequest;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dto.response.WarrantTypeResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.WarrantTypeService;

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
@RequestMapping("/api/warrant-types")
@RequiredArgsConstructor
public class WarrantTypeController {

    private final WarrantTypeService warrantTypeService;


    @PostMapping
    public ResponseEntity<ResponseDto<WarrantTypeDetailResponseDTO>> create(
            @Valid @RequestBody WarrantTypeRequest request) {
        return ResponseEntity.ok(warrantTypeService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<WarrantTypeDetailResponseDTO>> update(
            @Valid @RequestBody WarrantTypeRequest request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(warrantTypeService.update(request, id));
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(warrantTypeService.inactive(id));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(warrantTypeService.active(id));
    }
}
