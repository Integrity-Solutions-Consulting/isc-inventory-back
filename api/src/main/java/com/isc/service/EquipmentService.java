package com.isc.service;

import java.util.List;

import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentService {
    ResponseDto<List<EquipmentDetailResponseDTO>> getAllDetails();
    ResponseDto<List<EquipmentResponseDTO>> getSimpleList();
    ResponseDto<EquipmentDetailResponseDTO> save(EquipmentRequest request);
    ResponseDto<EquipmentDetailResponseDTO> update(EquipmentRequest request, Integer id);
    ResponseDto<MessageResponseDTO> inactive(Integer id);
    ResponseDto<MessageResponseDTO> active(Integer id);
    ResponseDto<MessageResponseDTO> cambiarEstado(Integer idEquipo, String nuevoEstadoNombre);
}
