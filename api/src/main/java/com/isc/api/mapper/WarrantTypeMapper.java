package com.isc.api.mapper;

import com.isc.api.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.api.dto.response.WarrantTypeResponseDTO;
import com.isc.api.entitys.WarrantTypeEntity;
import com.isc.api.entitys.EquipmentEntity;

public class WarrantTypeMapper {

    public static WarrantTypeResponseDTO toDto(WarrantTypeEntity entity) {
        if (entity == null) {
            return null;
        }

        WarrantTypeResponseDTO dto = new WarrantTypeResponseDTO();
        dto.setId(entity.getId());
        dto.setId_equipment(entity.getEquipment() != null ? entity.getEquipment().getId() : null);
        dto.setConditions(entity.getConditions());
        dto.setWarrantyStartDate(entity.getWarrantyStartDate());
        dto.setWarrantyEndDate(entity.getWarrantyEndDate());
        dto.setSupportContact(entity.getSupportContact());
        dto.setWarrantyStatus(entity.isWarrantyStatus());

        return dto;
    }

    public static WarrantTypeEntity fromDefaults(EquipmentEntity equipment) {
        WarrantTypeEntity entity = new WarrantTypeEntity();
        entity.setEquipment(equipment);
        entity.setConditions("Garantía estándar de 1 año");
        entity.setWarrantyStartDate(java.time.LocalDateTime.now());
        entity.setWarrantyEndDate(java.time.LocalDateTime.now().plusYears(1));
        entity.setSupportContact("soporte@empresa.com");
        entity.setWarrantyStatus(true);
        entity.setStatus(true);
        entity.setCreationUser("sistema");
        entity.setCreationDate(java.time.LocalDateTime.now());
        entity.setCreationIp("127.0.0.1");
        return entity;
    }
    public static WarrantTypeDetailResponseDTO toDetailResponseDTO(WarrantTypeEntity entity) {
        if (entity == null) {
            return null;
        }

        WarrantTypeDetailResponseDTO dto = new WarrantTypeDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setId_equipment(entity.getEquipment() != null ? entity.getEquipment().getId() : null);
        dto.setSerialNumber(entity.getEquipment() != null ? entity.getEquipment().getSerialNumber() : null);
        dto.setConditions(entity.getConditions());
        dto.setWarrantyStartDate(entity.getWarrantyStartDate());
        dto.setWarrantyEndDate(entity.getWarrantyEndDate());
        dto.setSupportContact(entity.getSupportContact());
        dto.setWarrantyStatus(entity.isWarrantyStatus());
        dto.setStatus(entity.isWarrantyStatus());
        dto.setCretionDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());

        return dto;
    }

    
}
