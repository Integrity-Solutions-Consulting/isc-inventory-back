package com.isc.mapper;

import com.isc.dto.response.SupplierDetailResponseDTO;
import com.isc.dto.response.SupplierResponseDTO;
import com.isc.entitys.SupplierEntity;

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