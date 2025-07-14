package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.InvoiceRequestDTO;
import com.isc.api.dto.response.InvoiceDetailResponseDTO;
import com.isc.api.dto.response.InvoiceResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.entitys.InvoiceEntity;

public interface InvoiceService {
	ResponseDto<List<InvoiceDetailResponseDTO>> getAllDetails();
	ResponseDto<List<InvoiceResponseDTO>> getSimpleList();
	InvoiceEntity save(InvoiceRequestDTO request);
	InvoiceEntity update(InvoiceRequestDTO request, Integer id);
	ResponseDto<MessageResponseDTO> inactive(Integer id);
	ResponseDto<MessageResponseDTO> active(Integer id);
}
