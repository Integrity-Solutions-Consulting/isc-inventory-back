package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.PositionRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.PositionDetailResponseDTO;
import com.isc.api.dto.response.PositionResponseDTO;
import com.isc.dtos.ResponseDto;

public interface PositionService {
	public ResponseDto<List<PositionDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<PositionResponseDTO>> getSimpleList();
	public ResponseDto<PositionDetailResponseDTO> save(PositionRequestDTO request);
	public ResponseDto<PositionDetailResponseDTO> update(PositionRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
