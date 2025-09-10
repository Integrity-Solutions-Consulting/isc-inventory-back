package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.isc.api.dto.request.EquipmentDismissalRequestDTO;
import com.isc.api.dto.response.EquipmentDismissalResponseDTO;
import com.isc.api.dto.response.EquipmentDismissalTypeResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.service.EquipmentDismissalService;
import com.isc.dtos.ResponseDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory/api/v1/equipment-dismissal")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','MANAGER') or hasAuthority('equipment_management')")
public class EquipmentDismissalController {

    private final EquipmentDismissalService dismissalService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentDismissalResponseDTO>>> getAll() 
    {
        return ResponseEntity.ok(dismissalService.getAllActive());
    }
    
    @GetMapping("/types")
    public ResponseEntity<ResponseDto<List<EquipmentDismissalTypeResponseDTO>>> getAllType()
    {
    	return ResponseEntity.ok(dismissalService.getAllActiveTypes());
    }
    
    @PostMapping("/saveDismissal")
    public ResponseEntity<ResponseDto<EquipmentDismissalResponseDTO>> saveDismissal(
    		@Valid @RequestBody EquipmentDismissalRequestDTO request) 
    {
        return ResponseEntity.ok(dismissalService.saveDismissal(request));
    }

    @PatchMapping("/inactiveDismissal/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(dismissalService.inactiveDismissal(id));
    }

    @PatchMapping("/activateDismissal/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(dismissalService.activeDismissal(id));
    }
}
