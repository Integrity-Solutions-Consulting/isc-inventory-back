package com.isc.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailResponseDTO {
	private Integer id;
	private Integer invoiceDetail;
	private Integer supplier;
	private LocalDateTime invoiceDate;
	private String invoiceNumber;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
}
