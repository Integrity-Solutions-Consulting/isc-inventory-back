package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentCategoryStockRequest;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentCategoryStockDetailResponseDTO;
import com.isc.dto.response.EquipmentCategoryStockResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentCategoryStockService {
	public ResponseDto<List<EquipmentCategoryStockDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentCategoryStockResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentCategoryStockDetailResponseDTO> save(EquipmentCategoryStockRequest request);
	public ResponseDto<EquipmentCategoryStockDetailResponseDTO> update(EquipmentCategoryStockRequest request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
	void inactiveByCategoryId(Integer categoryId);
    void activeByCategoryId(Integer categoryId);
    void incrementStock(Integer categoryId);
    void decrementStock(Integer categoryId);
}
