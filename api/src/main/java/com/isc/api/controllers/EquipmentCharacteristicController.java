package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.api.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.api.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentCharacteristicService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipment-characteristics")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','MANAGER') or hasAuthority('equipment_management')")
public class EquipmentCharacteristicController {

    private final EquipmentCharacteristicService characteristicService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentCharacteristicDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(characteristicService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<EquipmentCharacteristicResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(characteristicService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentCharacteristicDetailResponseDTO>> create(
            @Valid @RequestBody EquipmentCharacteristicRequestDTO request) {
        return ResponseEntity.ok(characteristicService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentCharacteristicDetailResponseDTO>> update(
            @PathVariable Integer id,
            @Valid@RequestBody EquipmentCharacteristicRequestDTO request) {
        return ResponseEntity.ok(characteristicService.update(request, id));
    }

    @PatchMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(characteristicService.inactive(id));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(characteristicService.active(id));
    }
}
