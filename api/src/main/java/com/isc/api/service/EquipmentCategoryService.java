package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentCategoryRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentCategoryService {
	public ResponseDto<List<EquipmentCategoryDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentCategoryResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentCategoryDetailResponseDTO> save(EquipmentCategoryRequestDTO request);
	public ResponseDto<EquipmentCategoryDetailResponseDTO> update(EquipmentCategoryRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
