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

    @GetMapping("/SimpleList")
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(assignmentService.getSimpleList());
    }

    @PostMapping("/Save")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> create(
            @Valid@RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.save(request));
    }

    @PutMapping("/{id}/ Update")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> update(
            @PathVariable Integer id,
           @Valid@RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.update(request, id));
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
