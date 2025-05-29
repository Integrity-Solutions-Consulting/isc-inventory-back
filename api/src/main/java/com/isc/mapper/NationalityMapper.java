package com.isc.mapper;

import com.isc.dto.response.NationalityDetailResponseDTO;
import com.isc.dto.response.NationalityResponseDTO;
import com.isc.entitys.NationalityEntity;

public class NationalityMapper {
	public static NationalityResponseDTO toSimpleDto(NationalityEntity entity) {
		if (entity == null)
			return null;
		return new NationalityResponseDTO(entity.getId(), entity.getDescription());
	}
	
	public static NationalityDetailResponseDTO toDetailDto(NationalityEntity entity) {
		if (entity == null)
			return null;
		return new NationalityDetailResponseDTO(entity.getId(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
