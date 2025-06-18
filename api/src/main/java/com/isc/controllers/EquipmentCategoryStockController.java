package com.isc.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.dto.request.EquipmentCategoryStockRequest;
import com.isc.dto.response.EquipmentCategoryStockDetailResponseDTO;
import com.isc.dto.response.EquipmentCategoryStockResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentCategoryStockService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipment-category-stock")
@RequiredArgsConstructor
public class EquipmentCategoryStockController {

    private final EquipmentCategoryStockService stockService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentCategoryStockDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(stockService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<EquipmentCategoryStockResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(stockService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentCategoryStockDetailResponseDTO>> create(
            @RequestBody EquipmentCategoryStockRequest request) {
        return ResponseEntity.ok(stockService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentCategoryStockDetailResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody EquipmentCategoryStockRequest request) {
        return ResponseEntity.ok(stockService.update(request, id));
    }

    @PatchMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(stockService.inactive(id));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(stockService.active(id));
    }
}
