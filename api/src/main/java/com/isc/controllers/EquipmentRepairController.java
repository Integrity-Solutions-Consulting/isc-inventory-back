package com.isc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isc.dto.request.EquipmentRepairRequestDTO;
import com.isc.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.EquipmentRepairService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/equipment-repairs")
public class EquipmentRepairController {

    @Autowired
    private EquipmentRepairService equipmentRepairService;

    @PostMapping
    public ResponseEntity<ResponseDto<EquipmentRepairDetailResponseDTO>> createRepair(
            @Valid @RequestBody EquipmentRepairRequestDTO request) {
        
        ResponseDto<EquipmentRepairDetailResponseDTO> response = equipmentRepairService.save(request);
        return new ResponseEntity<>(response, response.getMeta().getStatus());
    }
}
