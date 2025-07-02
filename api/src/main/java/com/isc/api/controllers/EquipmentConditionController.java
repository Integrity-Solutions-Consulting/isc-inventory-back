package com.isc.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.api.dto.request.EquipmentConditionRequestDTO;
import com.isc.api.dto.response.EquipmentConditionResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentConditionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/equipment-conditions")
public class EquipmentConditionController {

    @Autowired
    private EquipmentConditionService service;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<EquipmentConditionResponseDTO>>> getAllActive() {
        return ResponseEntity.ok(service.getDetails());
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<EquipmentConditionResponseDTO>> create(
            @Valid @RequestBody EquipmentConditionRequestDTO request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ResponseDto<EquipmentConditionResponseDTO>> update(
            @Valid @RequestBody EquipmentConditionRequestDTO request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(service.update(request, id));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> deactivate(@PathVariable Integer id) {
        return ResponseEntity.ok(service.inactive(id));
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activate(@PathVariable Integer id) {
        return ResponseEntity.ok(service.active(id));
    }
}
