package com.isc.auth.mapper;

import com.isc.auth.dto.response.PrivilegeResponseDTO;
import com.isc.auth.entitys.PrivilegeEntity;


public class PrivilegeMapper {
	 public static PrivilegeResponseDTO toDto(PrivilegeEntity entity) {
	        if (entity == null) return null;
	        return new PrivilegeResponseDTO(
	            entity.getId(),
	            entity.getKey(),
	            entity.getActive(),
	            entity.getApplicationId(), 
	            entity.getCreationDate(),
	            entity.getLastModificationDate()
	        );
	    }
}
