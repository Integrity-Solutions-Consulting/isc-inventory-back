package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequestDTO {
	private Integer name;
    private Integer address;
    private Integer email;
    private Integer phone;
}
