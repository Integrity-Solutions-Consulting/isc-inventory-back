package com.isc.api.mapper;

import com.isc.api.dto.response.SupplierDetailResponseDTO;
import com.isc.api.dto.response.SupplierResponseDTO;
import com.isc.api.entitys.SupplierEntity;

public class SupplierMapper {
	public static SupplierResponseDTO toSimpleDto(SupplierEntity entity) {
		if (entity == null)
			return null;
		return new SupplierResponseDTO(entity.getId(), entity.getBusinessName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getTaxId());
	}
	
	public static SupplierDetailResponseDTO toDetailDto(SupplierEntity entity) {
		if (entity == null)
			return null;
		return new SupplierDetailResponseDTO(entity.getId(), entity.getBusinessName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getTaxId(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}