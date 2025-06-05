package com.isc.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequest {
	private Integer invoiceDetail;
    private Integer supplier;
    private Integer invoiceDate;
    private Integer invoiceNumber;
}
