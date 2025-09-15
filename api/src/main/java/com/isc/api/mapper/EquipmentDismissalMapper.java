package com.isc.api.mapper;

import com.isc.api.dto.response.EquipmentDismissalResponseDTO;
import com.isc.api.entitys.EquipmentDismissalEntity;
import com.isc.api.entitys.EquipmentEntity;

public class EquipmentDismissalMapper 
{
	public static EquipmentDismissalResponseDTO toSimpleDto(EquipmentDismissalEntity entity) 
	{
	    if (entity == null) 
	        return null;
	    
	    EquipmentDismissalResponseDTO dto = new EquipmentDismissalResponseDTO();
	   	    		dto.setId(entity.getId());

	    EquipmentEntity equipment = entity.getEquipment();
	    if (entity.getEquipment() != null) 
	    {
	    	dto.setEquipmentId(equipment.getId());
	    	dto.setEquipmentBrand(equipment.getBrand());
	        dto.setEquipmentModel(equipment.getModel());
	        dto.setEquipmentSerialNumber(equipment.getSerialNumber());
	        dto.setEquipmentItemCode(equipment.getItemCode());
	        
	        if (equipment.getCondition() != null) {
	            dto.setConditionName(equipment.getCondition().getName());
	        }

	        if (equipment.getEquipStatus() != null) {
	            dto.setStatusName(equipment.getEquipStatus().getName());
	        }

	        if (equipment.getCategory() != null) {
	            dto.setCategoryName(equipment.getCategory().getName());
	        }

	        if (equipment.getCompany() != null) {
	            dto.setCompanyName(equipment.getCompany().getName());
	        }
	    }
	    	  
	    if (entity.getDismissalType() != null) 
	    {
            dto.setDismissalTypeId(entity.getDismissalType().getId());
            dto.setDismissalTypeName(entity.getDismissalType().getName());
        }

	    dto.setReason(entity.getReason());
        dto.setStatus(entity.getStatus());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());

        return dto;
	}
}
