package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
	private Integer businessName;
    private Integer address;
    private Integer phone;
    private Integer email;
    private Integer taxId;
}
