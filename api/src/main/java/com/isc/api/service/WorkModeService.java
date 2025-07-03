package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.WorkModeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.WorkModeDetailResponseDTO;
import com.isc.api.dto.response.WorkModeResponseDTO;
import com.isc.dtos.ResponseDto;

public interface WorkModeService {
	public ResponseDto<List<WorkModeDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<WorkModeResponseDTO>> getSimpleList();
	public ResponseDto<WorkModeDetailResponseDTO> save(WorkModeRequestDTO request);
	public ResponseDto<WorkModeDetailResponseDTO> update(WorkModeRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
