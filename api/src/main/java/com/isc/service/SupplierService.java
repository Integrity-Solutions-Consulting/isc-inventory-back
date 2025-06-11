package com.isc.service;

import java.util.List;

import com.isc.dto.request.SupplierRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.SupplierDetailResponseDTO;
import com.isc.dto.response.SupplierResponseDTO;
import com.isc.dtos.ResponseDto;

public interface SupplierService {
	public ResponseDto<List<SupplierDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<SupplierResponseDTO>> getSimpleList();
	public ResponseDto<SupplierDetailResponseDTO> save(SupplierRequestDTO request);
	public ResponseDto<SupplierDetailResponseDTO> update(SupplierRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
