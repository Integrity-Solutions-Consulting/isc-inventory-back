package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentService {
	public ResponseDto<List<EquipmentDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentDetailResponseDTO> save(EquipmentRequest request);
	public ResponseDto<EquipmentDetailResponseDTO> update(EquipmentRequest request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
