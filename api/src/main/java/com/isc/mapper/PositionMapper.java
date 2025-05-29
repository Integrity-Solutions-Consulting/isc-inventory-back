package com.isc.mapper;

import com.isc.dto.response.PositionDetailResponseDTO;
import com.isc.dto.response.PositionResponseDTO;
import com.isc.entitys.PositionEntity;

public class PositionMapper {
	public static PositionResponseDTO toSimpleDto(PositionEntity entity) {
		if (entity == null)
			return null;
		return new PositionResponseDTO(entity.getId(), entity.getName(), entity.getDescription());
	}
	
	public static PositionDetailResponseDTO toDetailDto(PositionEntity entity) {
		if (entity == null)
			return null;
		return new PositionDetailResponseDTO(entity.getId(), entity.getName(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
