package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.api.dto.request.EquipmentDismissalTypeRequestDTO;
import com.isc.api.dto.response.EquipmentDismissalTypeResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.service.EquipmentDismissalTypeService;
import com.isc.dtos.ResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/equipment-dismissal-type")
@RequiredArgsConstructor
public class EquipmentDismissalTypeController {

    private final EquipmentDismissalTypeService dismissalTypeService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentDismissalTypeResponseDTO>>> getAll() {
        return ResponseEntity.ok(dismissalTypeService.getAllActive());
    }

    @PostMapping("/saveDismissalType")
    public ResponseEntity<ResponseDto<EquipmentDismissalTypeResponseDTO>> save(
            @Valid @RequestBody EquipmentDismissalTypeRequestDTO request) {
        return ResponseEntity.ok(dismissalTypeService.saveDismissalType(request));
    }

    @PutMapping("/updateDismissalType/{id}")
    public ResponseEntity<ResponseDto<EquipmentDismissalTypeResponseDTO>> update(
            @PathVariable Integer id,
            @Valid @RequestBody EquipmentDismissalTypeRequestDTO request) {
        return ResponseEntity.ok(dismissalTypeService.updateDismissalType(request, id));
    }

    @PatchMapping("/inactiveDismissalType/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(dismissalTypeService.inactiveDismissalType(id));
    }

    @PatchMapping("/activateDismissalType/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(dismissalTypeService.activeDismissalType(id));
    }
}
