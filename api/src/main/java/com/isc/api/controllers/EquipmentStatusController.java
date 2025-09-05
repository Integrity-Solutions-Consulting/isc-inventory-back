package com.isc.api.controllers;

import com.isc.api.dto.request.EquipmentStatusRequestDTO;
import com.isc.api.dto.response.EquipmentStatusDetailResponseDTO;
import com.isc.api.dto.response.EquipmentStatusResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentStatusService;

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
@RequestMapping("/api/equipment-status")
@RequiredArgsConstructor

public class EquipmentStatusController {

    private final EquipmentStatusService equipmentStatusService;

    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<EquipmentStatusDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(equipmentStatusService.getAllDetails());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<EquipmentStatusResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(equipmentStatusService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentStatusDetailResponseDTO>> create(
            @Valid @RequestBody EquipmentStatusRequestDTO request) {
        return ResponseEntity.ok(equipmentStatusService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentStatusDetailResponseDTO>> update(
            @Valid @RequestBody EquipmentStatusRequestDTO request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(equipmentStatusService.update(request, id));
    }

    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentStatusService.inactive(id));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentStatusService.active(id));
    }
}
