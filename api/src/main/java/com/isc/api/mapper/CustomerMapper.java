package com.isc.api.mapper;

import com.isc.api.dto.request.CustomerRequestDTO;
import com.isc.api.dto.response.CustomerDetailResponseDTO;
import com.isc.api.dto.response.CustomerResponseDTO;
import com.isc.api.entitys.CustomerEntity;

public class CustomerMapper {

    public static CustomerEntity toEntity(CustomerRequestDTO dto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;
    }

    public static void updateEntityFromDTO(CustomerEntity entity, CustomerRequestDTO dto) {
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
    }

    public static CustomerResponseDTO toResponseDTO(CustomerEntity entity) {
        return new CustomerResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getAddress(),
            entity.getEmail(),
            entity.getPhone()
        );
    }

    public static CustomerDetailResponseDTO toDetailDTO(CustomerEntity entity) {
        return new CustomerDetailResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getAddress(),
            entity.getEmail(),
            entity.getPhone(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
