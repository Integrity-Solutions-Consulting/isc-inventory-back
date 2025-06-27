package com.isc.mapper;

import com.isc.dto.response.EquipmentConditionResponseDTO;
import com.isc.entitys.EquipmentConditionEntity;


public class EquipmentConditionMapper {

    public static EquipmentConditionResponseDTO toResponseDTO(EquipmentConditionEntity entity) {
        EquipmentConditionResponseDTO dto = new EquipmentConditionResponseDTO();
        dto.setId(entity.getId());
        dto.setConditionType(entity.getConditionType());
        dto.setStatus(entity.getStatus());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }
}
