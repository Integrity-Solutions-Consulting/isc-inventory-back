package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentRequest;
import com.isc.api.dto.request.InvoiceRequestDTO;
import com.isc.api.dto.request.WarrantTypeRequestDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.api.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.api.dto.response.EquipmentDetailResponseDTO;
import com.isc.api.dto.response.EquipmentResponseDTO;
import com.isc.api.dto.response.InvoiceDetailResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentService {
    ResponseDto<List<EquipmentDetailResponseDTO>> getAllDetails();
    ResponseDto<List<EquipmentResponseDTO>> getSimpleList();
    ResponseDto<EquipmentDetailResponseDTO> save(EquipmentRequest request);
    ResponseDto<EquipmentDetailResponseDTO> update(EquipmentRequest request, Integer id);
    ResponseDto<MessageResponseDTO> inactive(Integer id);
    ResponseDto<MessageResponseDTO> active(Integer id);
    ResponseDto<MessageResponseDTO> changeStatus(Integer idEquipo, Integer newStatus, Integer idRepair);
    ResponseDto<InvoiceDetailResponseDTO> setInvoice(Integer idEquipo,InvoiceRequestDTO request);
    ResponseDto<WarrantTypeDetailResponseDTO> setWarranty(Integer idEquip, WarrantTypeRequestDTO request);
    ResponseDto<EquipmentDetailResponseDTO> getFullEquipmentDetailById(Integer id);

}
