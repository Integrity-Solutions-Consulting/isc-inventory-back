package com.isc.mapper;

import com.isc.dto.response.WarrantTypeDetailResponseDTO;
import com.isc.dto.response.WarrantTypeResponseDTO;
import com.isc.entitys.WarrantTypeEntity;

public class WarrantTypeMapper {

    public static WarrantTypeResponseDTO toSimpleDto(WarrantTypeEntity entity) {
        if (entity == null) return null;

        return new WarrantTypeResponseDTO(
            entity.getId(),
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getConditions(),
            entity.getWarrantyStartDate(),
            entity.getWarrantyEndDate(),
            entity.getSupportContact(),
            entity.getWarrantyStatus()
        );
    }

    public static WarrantTypeDetailResponseDTO toDetailDto(WarrantTypeEntity entity) {
        if (entity == null) return null;

        return new WarrantTypeDetailResponseDTO(
            entity.getId(),
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getEquipment() != null ? entity.getEquipment().getSerialNumber() : null,
            entity.getConditions(),
            entity.getWarrantyStartDate(),
            entity.getWarrantyEndDate(),
            entity.getSupportContact(),
            entity.getWarrantyStatus(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
