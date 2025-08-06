package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentDismissalTypeRequestDTO;
import com.isc.api.dto.response.EquipmentDismissalTypeResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentDismissalTypeService {
    ResponseDto<List<EquipmentDismissalTypeResponseDTO>> getAllActive();
    ResponseDto<EquipmentDismissalTypeResponseDTO> saveDismissalType(EquipmentDismissalTypeRequestDTO request);
    ResponseDto<EquipmentDismissalTypeResponseDTO> updateDismissalType(EquipmentDismissalTypeRequestDTO request, Integer id);
    ResponseDto<MessageResponseDTO> inactiveDismissalType(Integer id);
    ResponseDto<MessageResponseDTO> activeDismissalType(Integer id);
}
