package com.isc.mapper;

import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.entitys.EquipmentAssignmentEntity;

public class EquipmentAssignmentMapper {
	public static EquipmentAssignmentResponseDTO toSimpleDto(EquipmentAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        return new EquipmentAssignmentResponseDTO(
            entity.getId(),
            entity.getEmployee() != null ? entity.getEmployee().getId() : null,
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getAssignmentDate(),
            entity.getReturnDate()
        );
    }
    
    public static EquipmentAssignmentDetailResponseDTO toDetailDto(EquipmentAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }
        
        String fullName = entity.getEmployee() != null ? 
            entity.getEmployee().getFirstName() + " " + entity.getEmployee().getLastName() : null;
        
        Integer identificationType = entity.getEmployee() != null && entity.getEmployee().getIdentificationType() != null ?
            entity.getEmployee().getIdentificationType().getId() : null;
        
        return new EquipmentAssignmentDetailResponseDTO(
            entity.getId(),
            entity.getEmployee() != null ? entity.getEmployee().getId() : null,
            identificationType,
            fullName,
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getEquipment() != null ? entity.getEquipment().getSerialNumber() : null,
            entity.getAssignmentDate(),
            entity.getReturnDate(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
