package com.isc.mapper;

import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.entitys.EquipmentEntity;

public class EquipmentMapper {
	public static EquipmentResponseDTO toSimpleDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentResponseDTO(
            entity.getId(),
            entity.getInvoice(),
            entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null,
            entity.getCategory() != null ? entity.getCategory().getId() : null,
            entity.getCompany() != null ? entity.getCompany().getId() : null,
            entity.getCharacteristic() != null ? entity.getCharacteristic().getId() : null,
            entity.getBrand(),
            entity.getModel(),
            entity.getSerialNumber(),
            entity.getItemCode()
        );
    }
    
    public static EquipmentDetailResponseDTO toDetailDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentDetailResponseDTO(
            entity.getId(),
            entity.getInvoice(),
            entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null,
            entity.getEquipStatus() != null ? entity.getEquipStatus().getName() : null,
            entity.getCategory() != null ? entity.getCategory().getId() : null,
            entity.getCompany() != null ? entity.getCompany().getId() : null,
            entity.getCompany() != null ? entity.getCompany().getName() : null,
            entity.getCharacteristic() != null ? entity.getCharacteristic().getId() : null,
            entity.getCharacteristic() != null ? entity.getCharacteristic().getDescription() : null,
            entity.getBrand(),
            entity.getModel(),
            entity.getSerialNumber(),
            entity.getItemCode(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
