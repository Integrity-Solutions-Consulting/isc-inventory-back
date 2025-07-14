package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.AppearanceRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.AppearanceDetailResponseDTO;
import com.isc.api.dto.response.AppearanceResponseDTO;
import com.isc.dtos.ResponseDto;

public interface AppearanceService {
	public ResponseDto<List<AppearanceDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<AppearanceResponseDTO>> getSimpleList();
	public ResponseDto<AppearanceDetailResponseDTO> save(AppearanceRequestDTO request);
	public ResponseDto<AppearanceDetailResponseDTO> update(AppearanceRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
