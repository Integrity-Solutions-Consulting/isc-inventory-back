package com.isc.api.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponseDTO {
	private Integer id;
	private Integer invoiceDetail;
	private Integer supplier;
	private LocalDate invoiceDate;
	private String invoiceNumber;
	
}
