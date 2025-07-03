package com.isc.api.mapper;

import com.isc.api.dto.response.EquipmentConditionResponseDTO;
import com.isc.api.entitys.EquipmentConditionEntity;


public class EquipmentConditionMapper {

    public static EquipmentConditionResponseDTO toResponseDTO(EquipmentConditionEntity entity) {
        EquipmentConditionResponseDTO dto = new EquipmentConditionResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStatus(entity.getStatus());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }
}
