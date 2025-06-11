package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentRepairRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.dto.response.EquipmentRepairResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentRepairService {
	public ResponseDto<List<EquipmentRepairDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentRepairResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentRepairDetailResponseDTO> save(EquipmentRepairRequestDTO request);
	public ResponseDto<EquipmentRepairDetailResponseDTO> update(EquipmentRepairRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
