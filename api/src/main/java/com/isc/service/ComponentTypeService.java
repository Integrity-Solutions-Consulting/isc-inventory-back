package com.isc.service;

import java.util.List;

import com.isc.dto.request.ComponentTypeRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.dto.response.ComponentTypeResponseDTO;
import com.isc.dtos.ResponseDto;

public interface ComponentTypeService {
	public ResponseDto<List<ComponentTypeDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<ComponentTypeResponseDTO>> getSimpleList();
	public ResponseDto<ComponentTypeDetailResponseDTO> save(ComponentTypeRequestDTO request);
	public ResponseDto<ComponentTypeDetailResponseDTO> update(ComponentTypeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
