package com.isc.api.mapper;

import com.isc.api.dto.response.ComponentTypeDetailResponseDTO;
import com.isc.api.dto.response.ComponentTypeResponseDTO;
import com.isc.api.entitys.ComponentTypeEntity;

public class ComponentTypeMapper {
	public static ComponentTypeResponseDTO toSimpleDto(ComponentTypeEntity entity) {
		if (entity == null)
			return null;
		return new ComponentTypeResponseDTO(entity.getId(), entity.getDescription());
	}
	
	public static ComponentTypeDetailResponseDTO toDetailDto(ComponentTypeEntity entity) {
		if (entity == null)
			return null;
		return new ComponentTypeDetailResponseDTO(entity.getId(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
