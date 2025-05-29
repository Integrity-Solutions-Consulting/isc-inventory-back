package com.isc.service;

import java.util.List;

import com.isc.dto.request.GenderRequestDTO;
import com.isc.dto.response.GenderDetailResponseDTO;
import com.isc.dto.response.GenderResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface GenderService {
	public ResponseDto<List<GenderDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<GenderResponseDTO>> getSimpleList();
	public ResponseDto<GenderDetailResponseDTO> save(GenderRequestDTO request);
	public ResponseDto<GenderDetailResponseDTO> update(GenderRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
