package com.isc.controllers;

import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.dto.response.ComponentTypeResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.ComponentTypeService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/component-types")
public class ComponentTypeController {

    
    private ComponentTypeService componentTypeService;

    // Crear nuevo tipo de componente
    @PostMapping
    public ResponseEntity<ResponseDto<ComponentTypeDetailResponseDTO>> createComponentType(
            @Valid @RequestBody ComponentTypeRequestDTO request) {
        return ResponseEntity.ok(componentTypeService.save(request));
    }

    // Actualizar componente
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ComponentTypeDetailResponseDTO>> updateComponentType(
            @Valid @RequestBody ComponentTypeRequestDTO request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(componentTypeService.update(request, id));
    }

    // Obtener lista detallada
    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<ComponentTypeDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(componentTypeService.getAllDetails());
    }

    // Obtener lista simple
    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<ComponentTypeResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(componentTypeService.getSimpleList());
    }

    // Inactivar tipo de componente
    @PutMapping("/{id}/inactive")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactiveComponentType(@PathVariable Integer id) {
        return ResponseEntity.ok(componentTypeService.inactive(id));
    }

    // Activar tipo de componente
    @PutMapping("/{id}/active")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> activeComponentType(@PathVariable Integer id) {
        return ResponseEntity.ok(componentTypeService.active(id));
    }
}
