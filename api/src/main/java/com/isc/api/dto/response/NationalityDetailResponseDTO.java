package com.isc.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NationalityDetailResponseDTO {
	private Integer id;
	private String description;
	private boolean active;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}
