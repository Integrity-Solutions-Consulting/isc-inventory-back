package com.isc.mapper;

import com.isc.dto.response.CustomerDetailResponseDTO;
import com.isc.dto.response.CustomerResponseDTO;
import com.isc.entitys.CustomerEntity;

public class CustomerMapper {
	public static CustomerResponseDTO toSimpleDto(CustomerEntity entity) {
		if (entity == null)
			return null;
		return new CustomerResponseDTO(entity.getId(), entity.getName(), entity.getAddress(), entity.getEmail(), entity.getPhone());
	}
	
	public static CustomerDetailResponseDTO toDetailDto(CustomerEntity entity) {
		if (entity == null)
			return null;
		return new CustomerDetailResponseDTO(entity.getId(), entity.getName(), entity.getAddress(), entity.getEmail(), entity.getPhone(),entity.getStatus(),entity.getCreationDate(),entity.getModificationDate());
	}
}
