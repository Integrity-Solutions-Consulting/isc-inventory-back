package com.isc.mapper;

import com.isc.dto.response.CompanyDetailResponseDTO;
import com.isc.dto.response.CompanyResponseDTO;
import com.isc.entitys.CompanyEntity;

public class CompanyMapper {
	public static CompanyResponseDTO toSimpleDto(CompanyEntity entity) {
		if (entity == null)
			return null;
		return new CompanyResponseDTO(entity.getId(), entity.getName(), entity.getDescription());
	}
	
	public static CompanyDetailResponseDTO toDetailDto(CompanyEntity entity) {
		if (entity == null)
			return null;
		return new CompanyDetailResponseDTO(entity.getId(), entity.getName(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
