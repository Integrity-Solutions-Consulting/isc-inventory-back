package com.isc.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
	private Integer idIdentificationType;
    private Integer idGender;
    private Integer idPosition;
    private Integer idWorkMode;
    private Integer idNationality;
    private String firstName;
    private String lastName;
    private String identification;
    private String phone;
    private String email;
    private String address;
    private LocalDateTime contractDate;
    private LocalDateTime contractEndDate;
}
