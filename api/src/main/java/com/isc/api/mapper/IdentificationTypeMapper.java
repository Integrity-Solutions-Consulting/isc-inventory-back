package com.isc.api.mapper;

import com.isc.api.dto.response.IdentificationTypeDetailResponseDTO;
import com.isc.api.dto.response.IdentificationTypeResponseDTO;
import com.isc.api.entitys.IdentificationTypeEntity;

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
