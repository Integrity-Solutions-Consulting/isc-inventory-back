package com.isc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentAssignmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipment-assignments")
public class EquipmentAssignmentController {

    @Autowired
    private EquipmentAssignmentService assignmentService;

    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(assignmentService.getAllDetails());
    }

    @GetMapping("/simpleList")
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(assignmentService.getSimpleList());
    }

    @PostMapping("/assign")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> assign(
            @Valid@RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.assign(request));
    }

    @PutMapping("/{id}/revoke")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> revoke(
            @PathVariable Integer id,
           @Valid@RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.revoke(request, id));
    }

    // Desactivar asignación
    @PutMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(assignmentService.inactive(id));
    }

    // Activar asignación
    @PutMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(assignmentService.active(id));
    }
}
