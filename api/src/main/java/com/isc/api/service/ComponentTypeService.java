package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.ComponentTypeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.api.dto.response.ComponentTypeResponseDTO;
import com.isc.dtos.ResponseDto;

public interface ComponentTypeService {
	public ResponseDto<List<ComponentTypeDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<ComponentTypeResponseDTO>> getSimpleList();
	public ResponseDto<ComponentTypeDetailResponseDTO> save(ComponentTypeRequestDTO request);
	public ResponseDto<ComponentTypeDetailResponseDTO> update(ComponentTypeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
