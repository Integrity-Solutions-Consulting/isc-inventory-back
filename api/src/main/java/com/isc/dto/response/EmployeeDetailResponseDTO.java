package com.isc.dto.response;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailResponseDTO {
	private Integer id;

    private IdentificationTypeResponseDTO identificationType;
    private GenderResponseDTO gender;
    private PositionResponseDTO position;
    private WorkModeResponseDTO workMode;
    private NationalityResponseDTO nationality;

    private String firstName;
    private String lastName;
    private String identification;
    private String phone;
    private String email;
    private String address;
    private String avatar;

    private LocalDateTime contractDate;
    private LocalDateTime contractEndDate;

    private Boolean status;

    private String creationUser;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
