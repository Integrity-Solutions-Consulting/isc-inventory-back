package com.isc.mapper;

import com.isc.dto.request.WarrantTypeRequest;
import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dto.response.WarrantTypeResponseDTO;
import com.isc.entitys.WarrantTypeEntity;

public class WarrantTypeMapper {

    public static WarrantTypeEntity toEntity(WarrantTypeRequest request) {
        WarrantTypeEntity entity = new WarrantTypeEntity();
        entity.setId_equipment(request.getId_equipment());
        entity.setConditions(request.getConditions());
        entity.setWarrantyStartDate(request.getWarrantyStartDate());
        entity.setWarrantyEndDate(request.getWarrantyEndDate());
        entity.setSupportContact(request.getSupportContact());
        entity.setWarrantyStatus(request.isWarrantyStatus());
        return entity;
    }

    public static WarrantTypeResponseDTO toResponseDTO(WarrantTypeEntity entity) {
        WarrantTypeResponseDTO dto = new WarrantTypeResponseDTO();
        dto.setId(entity.getId());
        dto.setId_equipment(entity.getId_equipment());
        dto.setConditions(entity.getConditions());
        dto.setWarrantyStartDate(entity.getWarrantyStartDate());
        dto.setWarrantyEndDate(entity.getWarrantyEndDate());
        dto.setSupportContact(entity.getSupportContact());
        dto.setWarrantyStatus(entity.isWarrantyStatus());
        return dto;
    }

    public static WarrantTypeDetailResponseDTO toDetailResponseDTO(WarrantTypeEntity entity, String serialNumber) {
        WarrantTypeDetailResponseDTO dto = new WarrantTypeDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setId_equipment(entity.getId_equipment());
        dto.setSerialNumber(serialNumber);
        dto.setConditions(entity.getConditions());
        dto.setWarrantyStartDate(entity.getWarrantyStartDate());
        dto.setWarrantyEndDate(entity.getWarrantyEndDate());
        dto.setSupportContact(entity.getSupportContact());
        dto.setWarrantyStatus(entity.isWarrantyStatus());
        dto.setStatus(entity.getStatus());
        dto.setCretionDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }
}
