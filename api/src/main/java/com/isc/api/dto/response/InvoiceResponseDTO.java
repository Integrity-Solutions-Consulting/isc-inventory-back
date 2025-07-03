package com.isc.api.dto.response;

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
	private LocalDateTime invoiceDate;
	private String invoiceNumber;
	
}
