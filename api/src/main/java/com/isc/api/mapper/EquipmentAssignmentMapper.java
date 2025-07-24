
package com.isc.api.mapper;


import com.isc.api.dto.response.EquipmentAssignmentResponseDTO;
import com.isc.api.dto.response.EquipmentResponseDTO;
import com.isc.api.dto.response.CompanyResponseDTO;
import com.isc.api.dto.response.EmployeeCatalogResponseDTO;
import com.isc.api.dto.response.EquipmentAssignmentDetailResponseDTO;
import com.isc.api.entitys.EquipmentAssignmentEntity;

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
        EmployeeCatalogResponseDTO employee = new EmployeeCatalogResponseDTO();
        if (entity.getEmployee() != null) {
            String firstName = entity.getEmployee().getFirstName() != null ? entity.getEmployee().getFirstName() : "";
            String lastName = entity.getEmployee().getLastName() != null ? entity.getEmployee().getLastName() : "";
            fullName = (firstName + " " + lastName).trim();
            employee.setId(entity.getEmployee().getId());
            employee.setFullName(fullName);
            employee.setEmail(entity.getEmployee().getEmail());
            employee.setIdentification(entity.getEmployee().getIdentification());
        }
        EquipmentResponseDTO equipment = new EquipmentResponseDTO();
        equipment.setId(entity.getEquipment().getId());
        equipment.setBrand(entity.getEquipment().getBrand());
        equipment.setCategory(entity.getEquipment().getCategory().getName());
        equipment.setItemCode(entity.getEquipment().getItemCode());
        equipment.setModel(entity.getEquipment().getModel());
        equipment.setSerialNumber(entity.getEquipment().getSerialNumber());
        equipment.setEquipmentStatusId(entity.getEquipment().getEquipStatus().getId());
        
        CompanyResponseDTO company = new CompanyResponseDTO();
        company.setId(entity.getEquipment().getCompany().getId());
        company.setName(entity.getEquipment().getCompany().getName());
        company.setTaxId(entity.getEquipment().getCompany().getTaxId());

        return new EquipmentAssignmentDetailResponseDTO(
            entity.getId(),
            employee,
            equipment,
            company,
            entity.getAssignmentDate(),
            entity.getReturnDate(),
            entity.getStatus()
        );
    }
}
