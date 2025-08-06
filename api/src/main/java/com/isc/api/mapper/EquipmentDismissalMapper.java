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
	    }
	    
	    if (entity.getDismissalType() != null) 
	    {
            dto.setDismissalTypeId(entity.getDismissalType().getId());
            dto.setDismissalTypeName(entity.getDismissalType().getName());
        }

        dto.setStatus(entity.getStatus());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());

        return dto;
	}
}
