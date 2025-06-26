package com.isc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTableResponseDTO {
	private Integer id;
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private Boolean status;
}
