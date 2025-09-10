package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.request.UpdateRepairStatusRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentRepairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/inventory/api/v1/equipment-repairs")
@PreAuthorize("hasAnyRole('ADMIN','MANAGER') or hasAuthority('equipment_management')")
@RequiredArgsConstructor

public class EquipmentRepairController {
	private final EquipmentRepairService equipmentRepairService;

    @PreAuthorize("hasAuthority('equipment_management')")
	@GetMapping("/allDetailList")
	public ResponseEntity<ResponseDto<List<EquipmentRepairDetailResponseDTO>>> getAllDetails() 
    {
		return ResponseEntity.ok(equipmentRepairService.getAllDetails());
	}

    @PreAuthorize("hasAuthority('equipment_management')")
	@PostMapping("/createEquipmentRepair")
	public ResponseEntity<ResponseDto<EquipmentRepairDetailResponseDTO>> createRepair(
			@Valid @RequestBody EquipmentRepairRequestDTO request) 
    {

		ResponseDto<EquipmentRepairDetailResponseDTO> response = equipmentRepairService.save(request);
		return new ResponseEntity<>(response, response.getMeta().getStatus());
	}

    @PreAuthorize("hasAuthority('equipment_management')")
	@PutMapping("/updateEquipmentRepair/{id}")
	public ResponseEntity<ResponseDto<EquipmentRepairDetailResponseDTO>> updateEquipmentRepair(@PathVariable Integer id,
			@Valid @RequestBody EquipmentRepairRequestDTO request) 
    {
		return ResponseEntity.ok(equipmentRepairService.update(request, id));
	}

    @PreAuthorize("hasAuthority('equipment_management')")
	@GetMapping("/simpleList")
	public ResponseEntity<ResponseDto<List<EquipmentRepairResponseDTO>>> getSimpleList() 
    {
		return ResponseEntity.ok(equipmentRepairService.getSimpleList());
	}

    @PreAuthorize("hasAuthority('equipment_management')")
	@PatchMapping("/inactive/{id}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> inactivateEquipment(@PathVariable Integer id) 
    {
		return ResponseEntity.ok(equipmentRepairService.inactive(id));
	}
}
