package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentStatusRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentStatusDetailResponseDTO;
import com.isc.dto.response.EquipmentStatusResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentStatusService {
	public ResponseDto<List<EquipmentStatusDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<EquipmentStatusResponseDTO>> getSimpleList();
	public ResponseDto<EquipmentStatusDetailResponseDTO> save(EquipmentStatusRequestDTO request);
	public ResponseDto<EquipmentStatusDetailResponseDTO> update(EquipmentStatusRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}