package com.isc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {
    private Integer id;
    private String name;
    private String taxId;
    private String email;
    private Boolean status;
}
