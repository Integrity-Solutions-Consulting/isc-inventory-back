package com.isc.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentConditionResponseDTO {
    private Integer id;
    private String conditionType;
    private Boolean status;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;

}
