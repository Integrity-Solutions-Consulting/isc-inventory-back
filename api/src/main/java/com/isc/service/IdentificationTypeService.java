package com.isc.service;

import java.util.List;

import com.isc.dto.request.IdentificationTypeRequestDTO;
import com.isc.dto.response.IdentificationTypeDetailResponseDTO;
import com.isc.dto.response.IdentificationTypeResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface IdentificationTypeService {
	public ResponseDto<List<IdentificationTypeDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<IdentificationTypeResponseDTO>> getSimpleList();
	public ResponseDto<IdentificationTypeDetailResponseDTO> save(IdentificationTypeRequestDTO request);
	public ResponseDto<IdentificationTypeDetailResponseDTO> update(IdentificationTypeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
