package com.isc.api.mapper;

import com.isc.api.dto.response.GenderDetailResponseDTO;
import com.isc.api.dto.response.GenderResponseDTO;
import com.isc.api.entitys.GenderEntity;

public class GenderMapper {
	public static GenderResponseDTO toSimpleDto(GenderEntity entity) {
		if (entity == null)
			return null;
		return new GenderResponseDTO(entity.getId(), entity.getDescription());
	}
	
	public static GenderDetailResponseDTO toDetailDto(GenderEntity entity) {
		if (entity == null)
			return null;
		return new GenderDetailResponseDTO(entity.getId(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
