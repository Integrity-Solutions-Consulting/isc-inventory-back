package com.isc.dto.response;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTableResponseDTO {
	private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private String address;
    private String email;
    private String phone;
    private Boolean status;
    private String identificationType;
    private String identification;
    private Integer idGender;
    private Integer idWorkMode;
    private Integer idNationality;
    private LocalDate contractDate;
    private LocalDate contractEndDate;
}
