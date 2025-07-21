package com.isc.api.mapper;

import com.isc.api.dto.request.EquipmentRepairRequestDTO;
import com.isc.api.dto.response.EquipmentRepairDetailResponseDTO;
import com.isc.api.dto.response.EquipmentRepairResponseDTO;
import com.isc.api.entitys.EquipmentEntity;
import com.isc.api.entitys.EquipmentRepairEntity;
import com.isc.api.entitys.EquipmentStatusEntity;

public class EquipmentRepairMapper 
{
	public static EquipmentRepairEntity toEntity(
		    EquipmentRepairRequestDTO request,
		    EquipmentEntity equipment
		) {
		    if (request == null) {
		        return null;
		    }

		    EquipmentRepairEntity entity = new EquipmentRepairEntity();
		    entity.setEquipment(equipment);
		    entity.setDescription(request.getDescription());
		    entity.setServiceProvider(request.getServiceProvider());
		    entity.setCost(request.getCost());

		    return entity;
		}



	public static EquipmentRepairResponseDTO toSimpleDto(EquipmentRepairEntity entityRepair) 
	{
	    if (entityRepair == null) 
	    {
	        return null;
	    }

	    EquipmentRepairResponseDTO dto = new EquipmentRepairResponseDTO();
	    dto.setId(entityRepair.getId());
	    dto.setEquipment(entityRepair.getEquipment() != null ? entityRepair.getEquipment().getId() : null);
	    dto.setRepairDate(entityRepair.getRepairDate());
	    dto.setDescription(entityRepair.getDescription());
	    dto.setCost(entityRepair.getCost());
	    dto.setServiceProvider(entityRepair.getServiceProvider());
	    dto.setRepairStatus(EquipmentStatusMapper.toSimpleDto(entityRepair.getRepairStatus())); // nuevo
	    return dto;
	}

    
	public static EquipmentRepairDetailResponseDTO toDetailDto(EquipmentRepairEntity entityRepair) {
	    if (entityRepair == null) {
	        return null;
	    }

	    EquipmentRepairDetailResponseDTO dto = new EquipmentRepairDetailResponseDTO();
	    dto.setId(entityRepair.getId());
	    dto.setEquipment(entityRepair.getEquipment() != null ? entityRepair.getEquipment().getId() : null);
	    dto.setSerialNumber(entityRepair.getEquipment().getSerialNumber());
	    // Estado de reparación específico
	    dto.setRepairStatus(entityRepair.getRepairStatus() != null 
	        ? EquipmentStatusMapper.toSimpleDto(entityRepair.getRepairStatus())
	        : null);

	    dto.setRepairDate(entityRepair.getRepairDate());
	    dto.setDescription(entityRepair.getDescription());
	    dto.setCost(entityRepair.getCost());
	    dto.setServiceProvider(entityRepair.getServiceProvider());
	    dto.setStatus(entityRepair.getStatus());
	    dto.setCreationDate(entityRepair.getCreationDate());
	    dto.setModificationDate(entityRepair.getModificationDate());

	    return dto;
	}

}
