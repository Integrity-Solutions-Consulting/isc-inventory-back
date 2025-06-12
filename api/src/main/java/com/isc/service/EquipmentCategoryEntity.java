package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentCategoryRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.dto.response.EquipmentCategoryResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentCategoryEntity {
	public ResponseDto<List<EquipmentCategoryDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentCategoryResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentCategoryDetailResponseDTO> save(EquipmentCategoryRequestDTO request);
	public ResponseDto<EquipmentCategoryDetailResponseDTO> update(EquipmentCategoryRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
