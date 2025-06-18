package com.isc.service;

import java.util.List;

import com.isc.dto.request.InvoiceDetailRequestDTO;
import com.isc.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface InvoiceDetailService {
	public ResponseDto<List<InvoiceDetailEntityDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<InvoiceDetailEntityResponseDTO>> getSimpleList();
	public ResponseDto<InvoiceDetailEntityDetailResponseDTO> save(InvoiceDetailRequestDTO request);
	public ResponseDto<InvoiceDetailEntityDetailResponseDTO> update(InvoiceDetailRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}