package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRepairRequestDTO {
	private Integer equipment;
    private Integer repairDate;
    private Integer description;
    private Integer cost;
    private Integer serviceProvider;
}
