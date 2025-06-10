package com.isc.service;

import java.util.List;

import com.isc.dto.request.InvoiceDetailRequestDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dtos.ResponseDto;

public interface InvoiceService {
	public ResponseDto<List<InvoiceDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<InvoiceResponseDTO>> getSimpleList();
	public ResponseDto<InvoiceDetailResponseDTO> save(InvoiceDetailRequestDTO request);
	public ResponseDto<InvoiceDetailResponseDTO> update(InvoiceDetailRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}
