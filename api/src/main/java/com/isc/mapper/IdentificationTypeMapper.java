package com.isc.mapper;

import com.isc.dto.response.IdentificationTypeDetailResponseDTO;
import com.isc.dto.response.IdentificationTypeResponseDTO;
import com.isc.entitys.IdentificationTypeEntity;

public class IdentificationTypeMapper {
	public static IdentificationTypeResponseDTO toSimpleDto(IdentificationTypeEntity entity) {
		if (entity == null)
			return null;
		return new IdentificationTypeResponseDTO(entity.getId(), entity.getDescription());
	}
	
	public static IdentificationTypeDetailResponseDTO toDetailDto(IdentificationTypeEntity entity) {
		if (entity == null)
			return null;
		return new IdentificationTypeDetailResponseDTO(entity.getId(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
