package com.isc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.dto.request.EquipmentConditionRequestDTO;
import com.isc.dto.response.EquipmentConditionResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentConditionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipment-conditions")
public class EquipmentConditionController {

    @Autowired
    private EquipmentConditionService service;

    @GetMapping("/List")
    public ResponseEntity<ResponseDto<List<EquipmentConditionResponseDTO>>> getAllActive() {
        return ResponseEntity.ok(service.getDetails());
    }

    @PostMapping("/Save")
    public ResponseEntity<ResponseDto<EquipmentConditionResponseDTO>> create(
            @Valid @RequestBody EquipmentConditionRequestDTO request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/{id}/Update")
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
