package com.isc.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeCatalogResponseDTO {
    private Integer id;
    private String fullName;
    private String identification;
    private String email;
}
