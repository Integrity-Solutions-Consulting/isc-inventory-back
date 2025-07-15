package com.isc.api.service;


import java.util.List;
import com.isc.api.dto.request.InvoiceDetailRequestDTO;
import com.isc.api.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.api.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.entitys.InvoiceDetailEntity;
import com.isc.dtos.ResponseDto;

public interface InvoiceDetailService {
	public ResponseDto<List<InvoiceDetailEntityDetailResponseDTO>> getAllDetails();
	public ResponseDto<List<InvoiceDetailEntityResponseDTO>> getSimpleList();
	public InvoiceDetailEntity save(InvoiceDetailRequestDTO request);
	public InvoiceDetailEntity update(InvoiceDetailRequestDTO request, Integer id);
	public ResponseDto<MessageResponseDTO> inactive(Integer id);
	public ResponseDto<MessageResponseDTO> active(Integer id);
}