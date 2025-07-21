package com.isc.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.EquipmentRepairService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/equipment-repairs")
@RequiredArgsConstructor

public class EquipmentRepairController {
	private final EquipmentRepairService equipmentRepairService;

	@GetMapping("/allDetailList")
	public ResponseEntity<ResponseDto<List<EquipmentRepairDetailResponseDTO>>> getAllDetails() {
		return ResponseEntity.ok(equipmentRepairService.getAllDetails());
	}

	@PostMapping("/createEquimentRepair")
	public ResponseEntity<ResponseDto<EquipmentRepairDetailResponseDTO>> createRepair(
			@Valid @RequestBody EquipmentRepairRequestDTO request) {

		ResponseDto<EquipmentRepairDetailResponseDTO> response = equipmentRepairService.save(request);
		return new ResponseEntity<>(response, response.getMeta().getStatus());
	}

	@PutMapping("/updateEquimentRepair/{id}")
	public ResponseEntity<ResponseDto<EquipmentRepairDetailResponseDTO>> updateEquipmentRepair(@PathVariable Integer id,
			@Valid @RequestBody EquipmentRepairRequestDTO request) {
		return ResponseEntity.ok(equipmentRepairService.update(request, id));
	}

	@GetMapping("/simpleList")
	public ResponseEntity<ResponseDto<List<EquipmentRepairResponseDTO>>> getSimpleList() {
		return ResponseEntity.ok(equipmentRepairService.getSimpleList());
	}

	@PatchMapping("/inactive/{id}")
	public ResponseEntity<ResponseDto<MessageResponseDTO>> inactivateEquipment(@PathVariable Integer id) {
		return ResponseEntity.ok(equipmentRepairService.inactive(id));
	}

}
