package com.isc.api.mapper;

import java.util.ArrayList;
import java.util.List;

import com.isc.api.dto.response.EquipmentResponseDTO;
import com.isc.api.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.api.dto.response.EquipmentConditionResponseDTO;
import com.isc.api.dto.response.EquipmentDetailResponseDTO;
import com.isc.api.entitys.*;

public class EquipmentMapper {

    public static EquipmentResponseDTO toSimpleDto(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }

        EquipmentResponseDTO dto = new EquipmentResponseDTO();
        dto.setId(entity.getId());
        dto.setCategory(entity.getCategory().getName());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setItemCode(entity.getItemCode());
        
        EquipmentConditionResponseDTO condition = new EquipmentConditionResponseDTO();
        condition.setId(entity.getCondition().getId());
        condition.setName(entity.getCondition().getName());

        dto.setCondition(condition);

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
        
        dto.setWarranty(entity.getWarranty()!=null ? entity.getWarranty().getId() : null);
        dto.setEquipmentStatusId(entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null);
        dto.setEquipmentStatusName(entity.getEquipStatus() != null ? entity.getEquipStatus().getName() : null);

        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        dto.setCategoryStock(entity.getCategory() != null ? entity.getCategory().getStock().getStock() : null);

        dto.setCompanyId(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setCompanyName(entity.getCompany() != null ? entity.getCompany().getName() : null);
        
        if (entity.getCondition() != null) 
        {
            dto.setEquipmentConditionId(entity.getCondition().getId());
            dto.setEquipmentConditionName(entity.getCondition().getName());
        }
        
        List<EquipmentCharacteristicResponseDTO> characteristics = new ArrayList<>();
        for(EquipmentCharacteristicEntity rq : entity.getCharacteristic()) {
        	characteristics.add(EquipmentCharacteristicMapper.toSimpleDto(rq));
        }

        dto.setCharacteristics(characteristics);

        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setItemCode(entity.getItemCode());
        dto.setStatus(entity.getStatus());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());

        return dto;
    }
    
    public static EquipmentDetailResponseDTO toDetailDtoFull(EquipmentEntity entity) {
        if (entity == null) {
            return null;
        }

        EquipmentDetailResponseDTO dto = new EquipmentDetailResponseDTO();
        dto.setId(entity.getId());
        if (entity.getInvoice() != null) dto.setInvoice(entity.getInvoice().getId());
        dto.setWarranty(entity.getWarranty() != null ? entity.getWarranty().getId() : null);
        dto.setEquipmentStatusId(entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null);
        dto.setEquipmentStatusName(entity.getEquipStatus() != null ? entity.getEquipStatus().getName() : null);
        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        dto.setCategoryStock(entity.getCategory() != null ? entity.getCategory().getStock().getStock() : null);
        dto.setCompanyId(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setCompanyName(entity.getCompany() != null ? entity.getCompany().getName() : null);
        dto.setEquipmentConditionId(entity.getCondition().getId());
        dto.setEquipmentConditionName(entity.getCondition().getName());

        List<EquipmentCharacteristicResponseDTO> characteristics = new ArrayList<>();
        for (EquipmentCharacteristicEntity ch : entity.getCharacteristic()) {
            characteristics.add(EquipmentCharacteristicMapper.toSimpleDto(ch));
        }
        dto.setCharacteristics(characteristics);

        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setSerialNumber(entity.getSerialNumber());
        dto.setItemCode(entity.getItemCode());
        dto.setStatus(entity.getStatus());
        dto.setCreationDate(entity.getCreationDate());
        dto.setModificationDate(entity.getModificationDate());

        return dto;
    }
}