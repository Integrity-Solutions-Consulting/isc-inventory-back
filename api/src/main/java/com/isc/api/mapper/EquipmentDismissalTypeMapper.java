package com.isc.api.mapper;

import com.isc.api.dto.response.EquipmentDismissalTypeResponseDTO;
import com.isc.api.entitys.EquipmentDismissalTypeEntity;

public class EquipmentDismissalTypeMapper 
{
	public static EquipmentDismissalTypeResponseDTO toSimpleDto(EquipmentDismissalTypeEntity entity) {
	    if (entity == null) return null;

	    EquipmentDismissalTypeResponseDTO dto = new EquipmentDismissalTypeResponseDTO();
	    dto.setId(entity.getId());
	    dto.setName(entity.getName());

	    return dto;
	}
}
