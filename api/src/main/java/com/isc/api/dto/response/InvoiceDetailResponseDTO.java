package com.isc.api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.isc.api.entitys.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailResponseDTO {
	private Integer id;
	private String description;
	private BigDecimal unitPrice;
	private Integer quantity;
	private BigDecimal subtotal;
	private BigDecimal tax;
	private BigDecimal discount;
	private BigDecimal total;
	private Integer invoiceDetail;
	private String supplier;
	private LocalDate invoiceDate;
	private String invoiceNumber;	
	
	
	private boolean status;	
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	
}
