package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.IdentificationTypeRequestDTO;
import com.isc.api.dto.response.IdentificationTypeDetailResponseDTO;
import com.isc.api.dto.response.IdentificationTypeResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface IdentificationTypeService {
	public ResponseDto<List<IdentificationTypeDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<IdentificationTypeResponseDTO>> getSimpleList();
	public ResponseDto<IdentificationTypeDetailResponseDTO> save(IdentificationTypeRequestDTO request);
	public ResponseDto<IdentificationTypeDetailResponseDTO> update(IdentificationTypeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
