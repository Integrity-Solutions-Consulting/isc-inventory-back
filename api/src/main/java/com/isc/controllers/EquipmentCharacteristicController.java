package com.isc.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.dto.request.EquipmentCharacteristicRequestDTO;
import com.isc.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentCharacteristicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipment-characteristics")
@RequiredArgsConstructor
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
            @RequestBody EquipmentCharacteristicRequestDTO request) {
        return ResponseEntity.ok(characteristicService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentCharacteristicDetailResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody EquipmentCharacteristicRequestDTO request) {
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
