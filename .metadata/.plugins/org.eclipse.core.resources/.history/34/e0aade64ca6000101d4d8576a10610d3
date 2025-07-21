package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.api.dto.request.EquipmentRequest;
import com.isc.api.dto.request.InvoiceRequestDTO;
import com.isc.api.dto.request.WarrantTypeRequestDTO;
import com.isc.api.dto.response.EquipmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentResponseDTO;
import com.isc.api.dto.response.InvoiceDetailResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/equipment")
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

    @PostMapping("/save")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> save(
            @RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.save(request));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> update(
            @PathVariable Integer id,
            @RequestBody EquipmentRequest request) {
        return ResponseEntity.ok(equipmentService.update(request, id));
    }
    @PutMapping("/changeStatus/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> changeStatus(
            @PathVariable("id") Integer idEquipo,
            @RequestBody Integer status) {

        ResponseDto<MessageResponseDTO> response = equipmentService.changeStatus(idEquipo, status);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.inactive(id));
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(equipmentService.active(id));
    }
    
    @PutMapping("/setInvoice/{id}")
    public ResponseEntity<ResponseDto<InvoiceDetailResponseDTO>> setInvoice(@PathVariable Integer id,
            @RequestBody InvoiceRequestDTO request) {
        return ResponseEntity.ok(equipmentService.setInvoice(id,request));
    }
    
    @PutMapping("/setWarranty/{id}")
    public ResponseEntity<ResponseDto<EquipmentDetailResponseDTO>> setWarranty(@PathVariable Integer id,
            @RequestBody WarrantTypeRequestDTO request) {
        return ResponseEntity.ok(equipmentService.setWarranty(id,request));
    }


}
