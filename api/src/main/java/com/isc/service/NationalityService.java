package com.isc.service;

import java.util.List;

import com.isc.dto.request.NationalityRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.NationalityDetailResponseDTO;
import com.isc.dto.response.NationalityResponseDTO;
import com.isc.dtos.ResponseDto;

public interface NationalityService {
	public ResponseDto<List<NationalityDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<NationalityResponseDTO>> getSimpleList();
	public ResponseDto<NationalityDetailResponseDTO> save(NationalityRequestDTO request);
	public ResponseDto<NationalityDetailResponseDTO> update(NationalityRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
