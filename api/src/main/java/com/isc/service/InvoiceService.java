package com.isc.service;

import java.util.List;

import com.isc.dto.request.InvoiceRequestDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.InvoiceDetailEntity;
import com.isc.entitys.InvoiceEntity;

public interface InvoiceService {
    ResponseDto<List<InvoiceDetailResponseDTO>> getAllDetails();
    ResponseDto<List<InvoiceResponseDTO>> getSimpleList();
    InvoiceEntity save(InvoiceRequestDTO request);
    InvoiceEntity update(InvoiceRequestDTO request, Integer id);
    ResponseDto<MessageResponseDTO> inactive(Integer id);
    ResponseDto<MessageResponseDTO> active(Integer id);
}
