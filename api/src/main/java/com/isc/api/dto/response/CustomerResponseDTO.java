package com.isc.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
	private Integer id;
	private String name;
	private String address;
	private String email;
	private String phone;
	
}
