package com.isc.mapper;

import java.util.ArrayList;
import java.util.List;

import com.isc.dto.request.EquipmentRequest;
import com.isc.dto.response.EquipmentResponseDTO;
import com.isc.dto.response.EquipmentCharacteristicResponseDTO;
import com.isc.dto.response.EquipmentDetailResponseDTO;
import com.isc.entitys.*;

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

        dto.setEquipmentStatusId(entity.getEquipStatus() != null ? entity.getEquipStatus().getId() : null);
        dto.setEquipmentStatusName(entity.getEquipStatus() != null ? entity.getEquipStatus().getName() : null);

        dto.setCategoryId(entity.getCategory() != null ? entity.getCategory().getId() : null);
        dto.setCategoryName(entity.getCategory() != null ? entity.getCategory().getName() : null);
        dto.setCategoryStock(entity.getCategory() != null ? entity.getCategory().getStock().getStock() : null);

        dto.setCompanyId(entity.getCompany() != null ? entity.getCompany().getId() : null);
        dto.setCompanyName(entity.getCompany() != null ? entity.getCompany().getName() : null);
        
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
    

}
