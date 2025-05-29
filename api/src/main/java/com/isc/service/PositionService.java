package com.isc.service;

import java.util.List;

import com.isc.dto.request.PositionRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.PositionDetailResponseDTO;
import com.isc.dto.response.PositionResponseDTO;
import com.isc.dtos.ResponseDto;

public interface PositionService {
	public ResponseDto<List<PositionDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<PositionResponseDTO>> getSimpleList();
	public ResponseDto<PositionDetailResponseDTO> save(PositionRequestDTO request);
	public ResponseDto<PositionDetailResponseDTO> update(PositionRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
