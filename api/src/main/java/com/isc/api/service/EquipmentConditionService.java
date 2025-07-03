package com.isc.api.service;

import java.util.List;
import com.isc.api.dto.request.EquipmentConditionRequestDTO;
import com.isc.api.dto.response.EquipmentConditionResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentConditionService 
{
	public ResponseDto<List<EquipmentConditionResponseDTO>>getDetails();
	public ResponseDto<EquipmentConditionResponseDTO> save(EquipmentConditionRequestDTO request);
	public ResponseDto<EquipmentConditionResponseDTO> update(EquipmentConditionRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}

