package com.isc.api.mapper;

import com.isc.api.dto.response.SupplierDetailResponseDTO;
import com.isc.api.dto.response.NationalityResponseDTO;
import com.isc.api.dto.response.SupplierResponseDTO;
import com.isc.api.dto.response.SupplierTypeResponseDTO;
import com.isc.api.entitys.SupplierEntity;

public class SupplierMapper 
{	
	public static SupplierResponseDTO toSimpleDto(SupplierEntity entity) 
	{
	    if (entity == null)
	        return null;

	    SupplierResponseDTO dto = new SupplierResponseDTO(
	        entity.getId(),
	        entity.getBusinessName(),
	        entity.getAddress(),
	        entity.getPhone(),
	        entity.getEmail(),
	        entity.getRuc(),
	        null,
	        null
	    );

	  
	    if (entity.getSupplierType() != null) {
	        SupplierTypeResponseDTO typeDto = new SupplierTypeResponseDTO();
	        typeDto.setId(entity.getSupplierType().getId());
	        typeDto.setName(entity.getSupplierType().getName());
	        dto.setSupplierType(typeDto);
	    }
	    
	    if (entity.getNationality() !=null) 
	    {
	    	NationalityResponseDTO typeDto=new NationalityResponseDTO();
	    	typeDto.setId(entity.getNationality().getId());
	    	typeDto.setDescription(entity.getNationality().getDescription());
	    	dto.setNationality(typeDto);
	    }

	    return dto;
	}


	
	public static SupplierDetailResponseDTO toDetailDto(SupplierEntity entity) 
	{
		if (entity == null)
			return null;
		return new SupplierDetailResponseDTO(
				entity.getId(), 
				entity.getBusinessName(), 
				entity.getAddress(), 
				entity.getPhone(),
				entity.getRuc(),
				entity.getEmail(),
				entity.getStatus(),
				entity.getCreationDate(),
				entity.getModificationDate());
	}
}