package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.SupplierRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.SupplierDetailResponseDTO;
import com.isc.api.dto.response.SupplierResponseDTO;
import com.isc.dtos.ResponseDto;

public interface SupplierService {
	public ResponseDto<List<SupplierDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<SupplierResponseDTO>> getSimpleList();
	public ResponseDto<SupplierDetailResponseDTO> save(SupplierRequestDTO request);
	public ResponseDto<SupplierDetailResponseDTO> update(SupplierRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
