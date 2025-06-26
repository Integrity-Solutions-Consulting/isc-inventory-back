package com.isc.controllers;

import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.dto.response.ComponentTypeResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.ComponentTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/component-types")
public class ComponentTypeController 
{
    private final ComponentTypeService componentTypeService;
 
    // Crea nuevo tipo de componente
    @PostMapping("/Save")
    public ResponseEntity<ResponseDto<ComponentTypeDetailResponseDTO>> createComponentType(
            @Valid @RequestBody ComponentTypeRequestDTO request) {
        return ResponseEntity.ok(componentTypeService.save(request));
    }

    // Actualiza el componente
    @PutMapping("/Update")
    public ResponseEntity<ResponseDto<ComponentTypeDetailResponseDTO>> updateComponentType(
            @Valid @RequestBody ComponentTypeRequestDTO request,
            @PathVariable Integer id) {
        return ResponseEntity.ok(componentTypeService.update(request, id));
    }

    // Obtiene lista detallada
    @GetMapping("/Details")
    public ResponseEntity<ResponseDto<List<ComponentTypeDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(componentTypeService.getAllDetails());
    }

    // Obtiene lista simple
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
