package com.isc.api.service;

import java.util.List;

import com.isc.api.dto.request.EquipmentDismissalRequestDTO;
import com.isc.api.dto.response.EquipmentDismissalResponseDTO;
import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;

public interface EquipmentDismissalService 
{
    ResponseDto<List<EquipmentDismissalResponseDTO>> getAllActive();
    ResponseDto<EquipmentDismissalResponseDTO> saveDismissal(EquipmentDismissalRequestDTO request);
    ResponseDto<EquipmentDismissalResponseDTO> updateDismissal(EquipmentDismissalRequestDTO request, Integer id);
    ResponseDto<MessageResponseDTO> activeDismissal(Integer id);
    ResponseDto<MessageResponseDTO> inactiveDismissal(Integer id);
}
