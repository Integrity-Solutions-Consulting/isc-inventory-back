package com.isc.mapper;

import com.isc.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.entitys.EquipmentCharacteristicEntity;

public class EquipmentCharacteristicMapper {
	public static EquipmentCharacteristicResponseDTO toSimpleDto(EquipmentCharacteristicEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentCharacteristicResponseDTO(
            entity.getId(),
            entity.getDescription(),
            entity.getComponent() != null ? entity.getComponent().getId() : null
        );
    }
    
    public static EquipmentCharacteristicDetailResponseDTO toDetailDto(EquipmentCharacteristicEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentCharacteristicDetailResponseDTO(
            entity.getId(),
            entity.getDescription(),
            entity.getComponent() != null ? entity.getComponent().getId() : null,
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
