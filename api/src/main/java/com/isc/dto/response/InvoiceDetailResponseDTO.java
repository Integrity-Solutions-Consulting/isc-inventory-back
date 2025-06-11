package com.isc.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailResponseDTO {
	private Integer id;
	private Integer category;
	private String description;
	private BigDecimal unitPrice;
	private Integer quantity;
	private BigDecimal subtotal;
	private BigDecimal tax;
	private BigDecimal discount;
	private BigDecimal total;
	private boolean status;
	private LocalDateTime cretionDate;
	private LocalDateTime modificationDate;
	
}
