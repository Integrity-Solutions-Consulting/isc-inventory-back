package com.isc.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.isc.dto.request.EmployeeRequestDTO;
import com.isc.dto.response.EmployeeCatalogResponseDTO;
import com.isc.dto.response.EmployeeDetailResponseDTO;
import com.isc.dto.response.EmployeeTableResponseDTO;
import com.isc.entitys.EmployeeEntity;
import com.isc.entitys.GenderEntity;
import com.isc.entitys.IdentificationTypeEntity;

public class EmployeeMapper {

    public static EmployeeDetailResponseDTO toDetailResponse(EmployeeEntity entity) {
        return new EmployeeDetailResponseDTO(
                entity.getId(),
                IdentificationTypeMapper.toSimpleDto(entity.getIdentificationType()),
                GenderMapper.toSimpleDto(entity.getGender()),
                PositionMapper.toSimpleDto(entity.getPosition()),
                WorkModeMapper.toSimpleDto(entity.getWorkMode()),
                NationalityMapper.toSimpleDto(entity.getNationality()),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdentification(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getAvatar(),
                entity.getContractDate(),
                entity.getContractEndDate(),
                entity.getStatus(),
                entity.getCreationUser(),
                entity.getCreationDate(),
                entity.getModificationDate()
        );
    }
    
    public static EmployeeTableResponseDTO toTableResponse(EmployeeEntity entity) {
        return new EmployeeTableResponseDTO(
        		entity.getId(),
        		entity.getFirstName(),
        		entity.getLastName(),
        		entity.getPosition().getName(),
        		entity.getAddress(),
        		entity.getEmail(),
        		entity.getPhone(),
        		entity.getStatus(),
        		entity.getIdentificationType().getDescription(),
        		entity.getIdentification(),
        		entity.getGender().getId(),
        		entity.getWorkMode().getId(),
        		entity.getNationality().getId(),
        		entity.getContractDate(),
        		entity.getContractEndDate()
        );
    }
    
    public static EmployeeCatalogResponseDTO toCatalogResponse(EmployeeEntity entity) {
        String fullName = entity.getFirstName() + " " + entity.getLastName();
        return new EmployeeCatalogResponseDTO(entity.getId(), fullName,entity.getIdentification(), entity.getEmail());
    }

    public static List<EmployeeTableResponseDTO> toTableResponseList(List<EmployeeEntity> entities) {
        return entities.stream()
                .map(EmployeeMapper::toTableResponse)
                .collect(Collectors.toList());
    }

    public static List<EmployeeCatalogResponseDTO> toCatalogResponseList(List<EmployeeEntity> entities) {
        return entities.stream()
                .map(EmployeeMapper::toCatalogResponse)
                .collect(Collectors.toList());
    }
}
