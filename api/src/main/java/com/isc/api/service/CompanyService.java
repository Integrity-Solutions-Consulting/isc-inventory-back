package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.CompanyRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.CompanyDetailResponseDTO;
import com.isc.api.dto.response.CompanyResponseDTO;
import com.isc.dtos.ResponseDto;

public interface CompanyService {
	public ResponseDto<List<CompanyDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<CompanyResponseDTO>> getSimpleList();
	public ResponseDto<CompanyDetailResponseDTO> save(CompanyRequestDTO request);
	public ResponseDto<CompanyDetailResponseDTO> update(CompanyRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
