package com.isc.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentAssignmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipment-assignments")
@RequiredArgsConstructor
public class EquipmentAssignmentController {

    private final EquipmentAssignmentService assignmentService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(assignmentService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<EquipmentAssignmentResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(assignmentService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> create(
            @RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentAssignmentDetailResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody EquipmentAssignmentRequestDTO request) {
        return ResponseEntity.ok(assignmentService.update(request, id));
    }

    @PatchMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(assignmentService.inactive(id));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(assignmentService.active(id));
    }
}
