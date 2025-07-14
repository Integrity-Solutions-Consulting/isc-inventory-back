package com.isc.api.mapper;

import java.time.LocalDateTime;

import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.entitys.EquipmentRepairEntity;

public class EquipmentRepairMapper 
{
	public static EquipmentRepairEntity toEntity(EquipmentRepairRequestDTO request, EquipmentEntity equipment) 
	{
        if(request == null) 
        {
            return null;
        }
        
        EquipmentRepairEntity entity = new EquipmentRepairEntity();
        entity.setEquipment(equipment);
        entity.setDescription(request.getDescription() != null ? request.getDescription() : null);
        entity.setServiceProvider(request.getServiceProvider() != null ? request.getServiceProvider() : null);
        entity.setCost(request.getCost());
        return entity;
    }

    public static EquipmentRepairResponseDTO toSimpleDto(EquipmentRepairEntity entityRepair)
    {
    	if(entityRepair==null) 
    	{
    		return null;
    	}
    	
    	EquipmentRepairResponseDTO dto = new EquipmentRepairResponseDTO();
    	dto.setId(entityRepair.getId() !=null ? entityRepair.getId():null );
    	dto.setEquipment(entityRepair.getEquipment() !=null ? entityRepair.getEquipment().getId():null);
    	dto.setRepairDate(entityRepair.getRepairDate());
    	dto.setDescription(entityRepair.getDescription() !=null ? entityRepair.getDescription():null);
    	dto.setCost(entityRepair.getCost() !=null ? entityRepair.getCost():null);
    	dto.setServiceProvider(entityRepair.getServiceProvider() !=null ? entityRepair.getServiceProvider():null);
    	
    	return dto;
       
    }
    
    public static EquipmentRepairDetailResponseDTO toDetailDto(EquipmentRepairEntity entityRepair)
    {
    	if(entityRepair==null) 
    	{
    		return null;
    	}
    EquipmentRepairDetailResponseDTO dto=new  EquipmentRepairDetailResponseDTO();
    dto.setId(entityRepair.getId() !=null ? entityRepair.getId():null );
    dto.setEquipment(entityRepair.getEquipment() !=null ? entityRepair.getEquipment().getId():null);
    dto.setSerialNumber(entityRepair.getEquipment().getSerialNumber());
    dto.setRepairDate(entityRepair.getRepairDate());
    dto.setDescription(entityRepair.getDescription() !=null ? entityRepair.getDescription():null);
    dto.setCost(entityRepair.getCost() !=null ? entityRepair.getCost():null);
    dto.setServiceProvider(entityRepair.getServiceProvider() !=null ? entityRepair.getServiceProvider():null);
    dto.setStatus(entityRepair.getStatus());
    dto.setCretionDate(entityRepair.getCreationDate());
    dto.setModificationDate(entityRepair.getModificationDate());
    
    return dto;
    }
    
    
    
}
