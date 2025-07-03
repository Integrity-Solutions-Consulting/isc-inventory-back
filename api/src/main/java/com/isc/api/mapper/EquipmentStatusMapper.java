package com.isc.api.mapper;

import com.isc.api.dto.response.EquipmentStatusDetailResponseDTO;
import com.isc.api.dto.response.EquipmentStatusResponseDTO;
import com.isc.api.entitys.EquipmentStatusEntity;

public class EquipmentStatusMapper {
	public static EquipmentStatusResponseDTO toSimpleDto(EquipmentStatusEntity entity) {
		if (entity == null)
			return null;
		return new EquipmentStatusResponseDTO(entity.getId(), entity.getName());
	}
	
	public static EquipmentStatusDetailResponseDTO toDetailDto(EquipmentStatusEntity entity) {
		if (entity == null)
			return null;
		return new EquipmentStatusDetailResponseDTO(entity.getId(), entity.getName(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}