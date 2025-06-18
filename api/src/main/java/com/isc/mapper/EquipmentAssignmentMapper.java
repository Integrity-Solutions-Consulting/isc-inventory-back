package com.isc.mapper;

import com.isc.dto.request.EquipmentAssignmentRequestDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.entitys.EquipmentAssignmentEntity;
import com.isc.entitys.EmployeeEntity;
import com.isc.entitys.EquipmentEntity;

public class EquipmentAssignmentMapper {

    public static EquipmentAssignmentEntity toEntity(EquipmentAssignmentRequestDTO dto, EmployeeEntity employee, EquipmentEntity equipment) {
        EquipmentAssignmentEntity entity = new EquipmentAssignmentEntity();
        entity.setEmployee(employee);
        entity.setEquipment(equipment);
        entity.setAssigmentDate(dto.getAssigmentDate());
        entity.setReturnDate(dto.getReturnDate());
        return entity;
    }

    public static EquipmentAssignmentResponseDTO toResponseDto(EquipmentAssignmentEntity entity) {
        EquipmentAssignmentResponseDTO dto = new EquipmentAssignmentResponseDTO();
        dto.setId(entity.getId());
        dto.setEmployee(entity.getEmployee().getId());
        dto.setEquipment(entity.getEquipment().getId());
        dto.setAssigmentDate(entity.getAssigmentDate());
        dto.setReturnDate(entity.getReturnDate());
        return dto;
    }

    public static EquipmentAssignmentDetailResponseDTO toDetailDto(EquipmentAssignmentEntity entity) {
        EquipmentAssignmentDetailResponseDTO dto = new EquipmentAssignmentDetailResponseDTO();
        dto.setId(entity.getId());
        dto.setEmployee(entity.getEmployee().getId());
        dto.setIdentificationType(entity.getEmployee().getIdentificationType().getId());
        dto.setFullName(entity.getEmployee().getFirstName());
        dto.setEquipment(entity.getEquipment().getId());
        dto.setSerialNumber(entity.getEquipment().getSerialNumber());
        dto.setAssigmentDate(entity.getAssigmentDate());
        dto.setReturnDate(entity.getReturnDate());
        dto.setStatus(entity.getStatus());
        dto.setCretionDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());
        return dto;
    }
}
