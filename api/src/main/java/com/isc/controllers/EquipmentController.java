package com.isc.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(equipmentService.getAllDetails());
    }

    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<EquipmentResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(equipmentService.getSimpleList());
    }

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> create(
            @RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.update(request, id));
    }
    @PutMapping("/{id}/estado")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> cambiarEstado(
            @PathVariable("id") Integer idEquipo,
            @RequestParam("estado") String nuevoEstadoNombre) {

        ResponseDto<MessageResponseDTO> response = equipmentService.cambiarEstado(idEquipo, nuevoEstadoNombre);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.inactive(id));
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.active(id));
    }
}
