package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.GenderRequestDTO;
import com.isc.api.dto.response.GenderDetailResponseDTO;
import com.isc.api.dto.response.GenderResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface GenderService {
	public ResponseDto<List<GenderDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<GenderResponseDTO>> getSimpleList();
	public ResponseDto<GenderDetailResponseDTO> save(GenderRequestDTO request);
	public ResponseDto<GenderDetailResponseDTO> update(GenderRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
