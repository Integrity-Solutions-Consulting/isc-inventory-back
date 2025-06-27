package com.isc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentResponseDTO {
    private Integer id;
    private String category;
    private String brand;
    private String model;
    private String serialNumber;
    private String itemCode;
}
