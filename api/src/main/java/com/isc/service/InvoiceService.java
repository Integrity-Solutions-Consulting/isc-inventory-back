package com.isc.service;

import java.util.List;

import com.isc.dto.request.InvoiceRequestDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface InvoiceService {
    ResponseDto<List<InvoiceDetailResponseDTO>> getAllDetails();
    ResponseDto<List<InvoiceResponseDTO>> getSimpleList();
    ResponseDto<InvoiceDetailResponseDTO> save(InvoiceRequestDTO request);
    ResponseDto<InvoiceDetailResponseDTO> update(InvoiceRequestDTO request, Integer id);
    ResponseDto<MessageResponseDTO> inactive(Integer id);
    ResponseDto<MessageResponseDTO> active(Integer id);
}
