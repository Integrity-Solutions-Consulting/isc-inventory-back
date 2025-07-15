package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.entitys.EquipmentRepairEntity;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentRepairService 
{
	public ResponseDto<List<EquipmentRepairDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentRepairResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentRepairDetailResponseDTO> save(EquipmentRepairRequestDTO request);
	public ResponseDto<EquipmentRepairDetailResponseDTO> update(EquipmentRepairRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public EquipmentRepairEntity registerRepairDate(Integer equipmentId);
}