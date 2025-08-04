package com.isc.api.mapper;

import com.isc.api.dto.response.EquipmentCharacteristicDetailResponseDTO;
import com.isc.api.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.api.dto.response.EquipmentCharacteristicsReportResponseDTO;
import com.isc.api.entitys.EquipmentCharacteristicEntity;

public class EquipmentCharacteristicMapper {
	public static EquipmentCharacteristicResponseDTO toSimpleDto(EquipmentCharacteristicEntity entity) {
		if (entity == null) {
			return null;
		}

		return new EquipmentCharacteristicResponseDTO(entity.getId(), entity.getDescription(),
				entity.getComponent() != null ? entity.getComponent().getId() : null,
				entity.getComponent() != null ? entity.getComponent().getDescription() : null);
	}

	public static EquipmentCharacteristicDetailResponseDTO toDetailDto(EquipmentCharacteristicEntity entity) {
		if (entity == null) {
			return null;
		}

		return new EquipmentCharacteristicDetailResponseDTO(entity.getId(), entity.getDescription(),
				entity.getComponent() != null ? entity.getComponent().getId() : null, entity.getStatus(),
				entity.getCreationDate(), entity.getModificationDate());
	}
	
	public static EquipmentCharacteristicsReportResponseDTO toReportDto(EquipmentCharacteristicEntity entity) {
		if (entity == null) {
			return null;
		}
		return new EquipmentCharacteristicsReportResponseDTO(entity.getComponent().getDescription(),entity.getDescription());
	}
}