package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.api.dto.request.EquipmentCategoryRequestDTO;
import com.isc.api.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/equipment-categories")
@RequiredArgsConstructor
public class EquipmentCategoryController {

    private final EquipmentCategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentCategoryDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(categoryService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<EquipmentCategoryResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(categoryService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentCategoryDetailResponseDTO>> create(
            @RequestBody EquipmentCategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentCategoryDetailResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody EquipmentCategoryRequestDTO request) {
        return ResponseEntity.ok(categoryService.update(request, id));
    }

    @PatchMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.inactive(id));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.active(id));
    }
}
