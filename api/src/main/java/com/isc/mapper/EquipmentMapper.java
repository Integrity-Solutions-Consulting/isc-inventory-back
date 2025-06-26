package com.isc.mapper;

import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.entitys.*;

public class EquipmentMapper {

    public static EquipmentEntity fromRequestDto(EquipmentRequest dto,
                                                 EquipmentStatusEntity status,
                                                 EquipmentCategoryEntity category,
                                                 CompanyEntity company)
                                                 {
        if (dto == null) {
            return null;
        }

        EquipmentEntity entity = new EquipmentEntity();
        entity.setEquipStatus(status);
        entity.setCategory(category);
        entity.setCompany(company);
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setSerialNumber(dto.getSerialNumber());
        entity.setItemCode(dto.getItemCode());

        // Crear garantía automáticamente
        WarrantTypeEntity warranty = WarrantTypeMapper.fromDefaults(entity);
        entity.setWarranty(warranty);

        return entity;
    }

    public static EquipmentResponseDTO toResponseDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }

        EquipmentResponseDTO dto = new EquipmentResponseDTO();
        dto.setId(entity.getId());
        dto.setInvoice(entity.getInvoice().getId());
        dto.setEquipStatus(entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null);
        dto.setCategory(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCompany(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setCharacteristic(entity.getCharacteristic() != null ? entity.getCharacteristic().getId() : null);
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setItemCode(entity.getItemCode());

        dto.setWarranty(WarrantTypeMapper.toDto(entity.getWarranty()));

        return dto;
    }

    public static EquipmentDetailResponseDTO toDetailDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }

        EquipmentDetailResponseDTO dto = new EquipmentDetailResponseDTO();
        dto.setId(entity.getId());
        if(entity.getInvoice()!=null) {
        	 dto.setInvoice(entity.getInvoice().getId());
        }

        dto.setEquipStatus(entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null);
        dto.setNameEquipStat(entity.getEquipStatus() != null ? entity.getEquipStatus().getName() : null);

        dto.setCategory(entity.getCategory() != null ? entity.getCategory().getId() : null);

        dto.setCompany(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setNameCompany(entity.getCompany() != null ? entity.getCompany().getName() : null);

        dto.setCharacteristic(entity.getCharacteristic() != null ? entity.getCharacteristic().getId() : null);
        dto.setDescriptionEquipChar(entity.getCharacteristic() != null ? entity.getCharacteristic().getDescription() : null);

        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setItemCode(entity.getItemCode());
        dto.setStatus(entity.getStatus());
        dto.setCretionDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());

        dto.setWarranty(WarrantTypeMapper.toDto(entity.getWarranty()));

        return dto;
    }
    
    public static EquipmentResponseDTO toSimpleDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }

        EquipmentResponseDTO dto = new EquipmentResponseDTO();
        dto.setId(entity.getId());
        dto.setInvoice(entity.getInvoice().getId());
        dto.setEquipStatus(entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null);
        dto.setCategory(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCompany(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setCharacteristic(entity.getCharacteristic() != null ? entity.getCharacteristic().getId() : null);
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setItemCode(entity.getItemCode());
        return dto;
    }

}
