package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.api.dto.request.EquipmentRepairStatusChangeRequestDTO;
import com.isc.api.dto.request.EquipmentRequest;
import com.isc.api.dto.request.InvoiceRequestDTO;
import com.isc.api.dto.request.WarrantTypeRequestDTO;
import com.isc.api.dto.response.EquipmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentResponseDTO;
import com.isc.api.dto.response.InvoiceDetailResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dtos.ResponseDto;

import jakarta.validation.Valid;

import com.isc.api.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory/api/v1/equipment")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','MANAGER') or hasAuthority('equipment_management')")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PreAuthorize("hasAuthority('equipment_management')")
    @GetMapping
    public ResponseEntity<ResponseDto<List<EquipmentDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(equipmentService.getAllDetails());
    }

    @PreAuthorize("hasAuthority('equipment_management')")
    @GetMapping("/simple")
    public ResponseEntity<ResponseDto<List<EquipmentResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(equipmentService.getSimpleList());
    }
    
    @PreAuthorize("hasAuthority('equipment_management')")
    @GetMapping("/detail/{id}")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> getFullDetailById(@PathVariable Integer id) {
        ResponseDto<EquipmentDetailResponseDTO> response = equipmentService.getFullEquipmentDetailById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('equipment_management')")
    @PostMapping("/save")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> save(
            @Valid @RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.save(request));
    }

    @PreAuthorize("hasAuthority('equipment_management')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> update(
            @PathVariable Integer id,
            @Valid @RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.update(request, id));
    }
    
    @PreAuthorize("hasAuthority('equipment_management')")
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> changeStatus(
            @PathVariable("id") Integer idEquipo,
            @Valid @RequestBody EquipmentRepairStatusChangeRequestDTO request) {

        ResponseDto<MessageResponseDTO> response = equipmentService.changeStatus(idEquipo, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAuthority('equipment_management')")
    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.inactive(id));
    }

    @PreAuthorize("hasAuthority('equipment_management')")
    @PatchMapping("/activate/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.active(id));
    }
    
    @PreAuthorize("hasAuthority('equipment_management')")
    @PutMapping("/setInvoice/{id}")
    public ResponseEntity<ResponseDto<InvoiceDetailResponseDTO>> setInvoice(@PathVariable Integer id,
    		@Valid @RequestBody InvoiceRequestDTO request) {
        return ResponseEntity.ok(equipmentService.setInvoice(id,request));
    }
    
    @PreAuthorize("hasAuthority('equipment_management')")
    @PutMapping("/setWarranty/{id}")
    public ResponseEntity<ResponseDto<WarrantTypeDetailResponseDTO>> setWarranty(@PathVariable Integer id,
            @Valid@RequestBody WarrantTypeRequestDTO request) {
        return ResponseEntity.ok(equipmentService.setWarranty(id,request));
    }


}
