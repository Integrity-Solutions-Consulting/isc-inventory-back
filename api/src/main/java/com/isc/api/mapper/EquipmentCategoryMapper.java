package com.isc.api.mapper;

import com.isc.api.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCategoryResponseDTO;
import com.isc.api.entitys.EquipmentCategoryEntity;

public class EquipmentCategoryMapper {
	public static EquipmentCategoryResponseDTO toSimpleDto(EquipmentCategoryEntity entity) {
		if (entity == null)
			return null;
		return new EquipmentCategoryResponseDTO(entity.getId(), entity.getName());
	}
	
	public static EquipmentCategoryDetailResponseDTO toDetailDto(EquipmentCategoryEntity entity) {
		if (entity == null)
			return null;
		return new EquipmentCategoryDetailResponseDTO(entity.getId(), entity.getName(), entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}