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
	private String description;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}