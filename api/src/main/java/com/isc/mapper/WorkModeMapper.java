package com.isc.mapper;

import com.isc.dto.response.WorkModeDetailResponseDTO;
import com.isc.dto.response.WorkModeResponseDTO;
import com.isc.entitys.WorkModeEntity;

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
