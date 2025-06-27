package com.isc.api.dto.response;


import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDetailResponseDTO {
    private Integer id;
    private Integer invoice;

    private Integer equipmentStatusId;
    private String equipmentStatusName;

    private Integer categoryId;
    private String categoryName;
    private Integer categoryStock;

    private Integer companyId;
    private String companyName;

    private List<EquipmentCharacteristicResponseDTO> characteristics;

    private String brand;
    private String model;
    private String SerialNumber;
    private String itemCode;
    
    private boolean status;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
