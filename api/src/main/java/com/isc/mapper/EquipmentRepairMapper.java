package com.isc.mapper;

import com.isc.dto.request.EquipmentRepairRequestDTO;
import com.isc.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.dto.response.EquipmentRepairResponseDTO;
import com.isc.entitys.EquipmentEntity;
import com.isc.entitys.EquipmentRepairEntity;

public class EquipmentRepairMapper {

    public static EquipmentRepairEntity toEntity(EquipmentRepairRequestDTO dto, EquipmentEntity equipment) {
        EquipmentRepairEntity entity = new EquipmentRepairEntity();
        entity.setEquipment(equipment);
        entity.setRepairDate(dto.getRepairDate());
        entity.setDescription(dto.getDescription());
        entity.setCost(dto.getCost());
        entity.setServiceProvider(dto.getServiceProvider());
        return entity;
    }
    
    public static EquipmentRepairResponseDTO toSimpleDTO(EquipmentRepairEntity entity) {
        EquipmentRepairResponseDTO dto = new EquipmentRepairResponseDTO();
        dto.setId(entity.getId());
        dto.setEquipment(entity.getEquipment().getId());
        dto.setRepairDate(entity.getRepairDate());
        dto.setDescription(entity.getDescription());
        dto.setCost(entity.getCost());
        dto.setServiceProvider(entity.getServiceProvider());
        return dto;
    }

    public static EquipmentRepairDetailResponseDTO toDetailDTO(EquipmentRepairEntity entity) {
        EquipmentRepairDetailResponseDTO dto = new EquipmentRepairDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setEquipment(entity.getEquipment().getId());
        dto.setSerialNumber(entity.getEquipment().getSerialNumber());
        dto.setRepairDate(entity.getRepairDate());
        dto.setDescription(entity.getDescription());
        dto.setCost(entity.getCost());
        dto.setServiceProvider(entity.getServiceProvider());
        dto.setStatus(entity.getStatus());
        dto.setCretionDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }
}
