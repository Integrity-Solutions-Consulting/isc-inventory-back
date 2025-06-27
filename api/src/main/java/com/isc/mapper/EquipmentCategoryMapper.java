package com.isc.mapper;

import com.isc.dto.response.EquipmentCategoryDetailResponseDTO;
import com.isc.dto.response.EquipmentCategoryResponseDTO;
import com.isc.entitys.EquipmentCategoryEntity;

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