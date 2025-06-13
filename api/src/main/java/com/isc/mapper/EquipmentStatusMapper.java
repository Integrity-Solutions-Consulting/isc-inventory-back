package com.isc.mapper;

import com.isc.dto.response.EquipmentStatusDetailResponseDTO;
import com.isc.dto.response.EquipmentStatusResponseDTO;
import com.isc.entitys.EquipmentStatusEntity;

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
