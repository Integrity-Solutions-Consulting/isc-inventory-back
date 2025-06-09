package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentStatusDetailResponseDTO {
	private Integer id;
	private String name;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}
