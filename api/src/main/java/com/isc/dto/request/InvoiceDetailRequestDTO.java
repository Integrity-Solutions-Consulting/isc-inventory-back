package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailRequestDTO {
	private Integer category;
    private Integer description;
    private Integer unitPrice;
    private Integer quantity;
    private Integer subtotal;
    private Integer tax;
    private Integer discount;
    private Integer total;
}
