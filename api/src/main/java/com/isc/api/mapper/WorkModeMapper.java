package com.isc.api.mapper;

import com.isc.api.dto.response.WorkModeDetailResponseDTO;
import com.isc.api.dto.response.WorkModeResponseDTO;
import com.isc.api.entitys.WorkModeEntity;

public class WorkModeMapper {
	public static WorkModeResponseDTO toSimpleDto(WorkModeEntity entity) {
		if (entity == null)
			return null;
		return new WorkModeResponseDTO(entity.getId(), entity.getName(), entity.getDescription());
	}
	
	public static WorkModeDetailResponseDTO toDetailDto(WorkModeEntity entity) {
		if (entity == null)
			return null;
		return new WorkModeDetailResponseDTO(entity.getId(), entity.getName(), entity.getDescription(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
