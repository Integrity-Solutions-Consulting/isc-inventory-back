package com.isc.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDismissalResponseDTO {
		private Integer id;
		
	    private Integer equipmentId;
	    
	    private String equipmentBrand;
	    private String equipmentModel;
	    private String equipmentSerialNumber;
	    private String equipmentItemCode;
	    
	    private String conditionName;
	    private String statusName;
	    private String categoryName;
	    private String companyName;
	    
	    private Integer dismissalTypeId;
	    private String dismissalTypeName;
	    private String reason;
	    
	    private Boolean status;
	    private LocalDateTime creationDate;
	    private LocalDateTime modificationDate;
}