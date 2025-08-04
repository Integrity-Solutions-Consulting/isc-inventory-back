package com.isc.api.dto.response;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailEntityDetailResponseDTO {
	
	private Integer id;
	private String description;
	private BigDecimal unitPrice;
	private Integer quantity;
	private BigDecimal subtotal;
	private BigDecimal tax;
	private BigDecimal discount;
	private BigDecimal total;
	private boolean status;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;

}