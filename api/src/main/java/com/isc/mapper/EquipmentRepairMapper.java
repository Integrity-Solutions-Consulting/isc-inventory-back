package com.isc.mapper;

import com.isc.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.dto.response.EquipmentRepairResponseDTO;
import com.isc.entitys.EquipmentRepairEntity;
import java.math.BigDecimal;

public class EquipmentRepairMapper {
	public static EquipmentRepairResponseDTO toSimpleDto(EquipmentRepairEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentRepairResponseDTO(
            entity.getId(),
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getRepairDate(),
            entity.getDescription(),
            entity.getCost(),
            entity.getServiceProvider()
        );
    }
    
    public static EquipmentRepairDetailResponseDTO toDetailDto(EquipmentRepairEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentRepairDetailResponseDTO(
            entity.getId(),
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getEquipment() != null ? entity.getEquipment().getSerialNumber() : null,
            entity.getRepairDate(),
            entity.getDescription(),
            entity.getCost(),
            entity.getServiceProvider(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
