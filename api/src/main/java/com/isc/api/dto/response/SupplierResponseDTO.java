package com.isc.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierResponseDTO {
	private Integer id;
	private String businessName;
	private String address;
	private String phone;
	private String email;
	private String taxId;
	
}
