package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetailResponseDTO {
	private Integer id;
	private String name;
	private String address;
	private String email;
	private String phone;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}
