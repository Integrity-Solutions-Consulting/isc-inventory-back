package com.isc.mapper;


import com.isc.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.entitys.EquipmentAssignmentEntity;

public class EquipmentAssignmentMapper {

    public static EquipmentAssignmentResponseDTO toResponseDTO(EquipmentAssignmentEntity entity) {
        return new EquipmentAssignmentResponseDTO(
            entity.getId(),
            entity.getEmployee() != null ? entity.getEmployee().getId() : null,
            entity.getEquipment() != null ? entity.getEquipment().getId() : null,
            entity.getAssignmentDate(),
            entity.getReturnDate()
        );
    }

    public static EquipmentAssignmentDetailResponseDTO toDetailDTO(EquipmentAssignmentEntity entity) {
        String fullName = null;
        if (entity.getEmployee() != null) {
            String firstName = entity.getEmployee().getFirstName() != null ? entity.getEmployee().getFirstName() : "";
            String lastName = entity.getEmployee().getLastName() != null ? entity.getEmployee().getLastName() : "";
            fullName = (firstName + " " + lastName).trim();
        }

        return new EquipmentAssignmentDetailResponseDTO(
            entity.getId(),
            entity.getEmployee() != null ? entity.getEmployee().getId() : null,
            entity.getEmployee() != null && entity.getEmployee().getIdentificationType() != null
                ? entity.getEmployee().getIdentificationType().getId()
                : null,
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
