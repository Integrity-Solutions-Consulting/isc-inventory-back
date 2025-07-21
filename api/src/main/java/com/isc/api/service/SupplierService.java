package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.SupplierRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.SupplierDetailResponseDTO;
import com.isc.api.dto.response.SupplierResponseDTO;
import com.isc.dtos.ResponseDto;

public interface SupplierService 
{
	ResponseDto<List<SupplierDetailResponseDTO>> getAllDetails();
	ResponseDto<List<SupplierResponseDTO>> getSimpleList();
	ResponseDto<SupplierDetailResponseDTO> save(SupplierRequestDTO request);
    ResponseDto<SupplierDetailResponseDTO> update(SupplierRequestDTO request, Integer id);
	ResponseDto<MessageResponseDTO> inactive(Integer id);
	ResponseDto<MessageResponseDTO> active(Integer id);
}
