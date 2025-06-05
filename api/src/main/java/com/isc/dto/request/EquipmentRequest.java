package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentRequest {
	private Integer invoice;
    private Integer EquipStatus;
    private Integer category;
    private Integer company;
    private Integer characteristic;
    private Integer brand;
    private Integer model;
    private Integer SerialNumber;
    private Integer itemCode;
}
