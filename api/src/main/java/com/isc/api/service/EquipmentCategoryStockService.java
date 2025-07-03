package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentCategoryStockRequest;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryStockDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryStockResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentCategoryStockService {
	public ResponseDto<List<EquipmentCategoryStockDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentCategoryStockResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentCategoryStockDetailResponseDTO> save(EquipmentCategoryStockRequest request);
	public ResponseDto<EquipmentCategoryStockDetailResponseDTO> update(EquipmentCategoryStockRequest request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
