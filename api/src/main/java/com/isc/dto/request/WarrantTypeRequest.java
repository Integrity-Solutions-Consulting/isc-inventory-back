package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarrantTypeRequest {
	private Integer equipment;
    private Integer conditions;
    private Integer warrantyStartDate;
    private Integer warrantyEndDate;
    private Integer SupportContact;
    private Integer warrantyStatus;
}
