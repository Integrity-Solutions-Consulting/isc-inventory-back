package com.isc.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRepairStatusRequestDTO 
{
	private Integer repairId;
    private Integer repairStatusId;
}
