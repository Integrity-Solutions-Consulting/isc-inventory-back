package com.isc.auth.service;

import java.util.List;

import com.isc.auth.dto.request.ApplicationRequestDTO;
import com.isc.auth.dto.response.ApplicationResponseDTO;
import com.isc.auth.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface ApplicationService {
	public ResponseDto<List<ApplicationResponseDTO>> getAll();
	public ResponseDto<ApplicationResponseDTO> save(ApplicationRequestDTO request);
	public ResponseDto<ApplicationResponseDTO> update(ApplicationRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
