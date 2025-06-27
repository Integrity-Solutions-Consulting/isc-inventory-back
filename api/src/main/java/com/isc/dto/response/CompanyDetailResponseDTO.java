package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDetailResponseDTO {
    private Integer id;
    private String name;
    private String taxId;
    private String address;
    private String phone;
    private String email;
    private Boolean status;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
