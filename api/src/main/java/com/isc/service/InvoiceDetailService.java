package com.isc.service;

import java.util.List;

import com.isc.dto.request.InvoiceDetailRequestDTO;
import com.isc.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.InvoiceDetailEntity;

public interface InvoiceDetailService {
	public ResponseDto<List<InvoiceDetailEntityDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<InvoiceDetailEntityResponseDTO>> getSimpleList();
	public InvoiceDetailEntity save(InvoiceDetailRequestDTO request);
	public InvoiceDetailEntity update(InvoiceDetailRequestDTO request);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}