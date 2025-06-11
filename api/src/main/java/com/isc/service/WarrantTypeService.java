package com.isc.service;

import java.util.List;

import com.isc.dto.request.WarrantTypeRequest;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dto.response.WarrantTypeResponseDTO;
import com.isc.dtos.ResponseDto;

public interface WarrantTypeService {
	public ResponseDto<List<WarrantTypeDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<WarrantTypeResponseDTO>> getSimpleList();
	public ResponseDto<WarrantTypeDetailResponseDTO> save(WarrantTypeRequest request);
	public ResponseDto<WarrantTypeDetailResponseDTO> update(WarrantTypeRequest request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}