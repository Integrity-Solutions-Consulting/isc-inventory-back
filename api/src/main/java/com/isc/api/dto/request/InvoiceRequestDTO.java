package com.isc.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDTO {
	private Integer id;
	
	@NotNull(message = "El detalle de la factura es obligatorio")
	private InvoiceDetailRequestDTO invoiceDetail;
    
	@NotNull(message = "El proovedor de la factura es obligatorio")
	private Integer supplier;
    
	@NotNull(message = "La fecha de la factura es obligatoria")
    @FutureOrPresent(message = "La fecha de la factura no puede estar en el pasado")
	private LocalDateTime invoiceDate;
   
	@NotBlank(message = "El número de factura no puede estar vacío")
    @Size(max = 50, message = "El número de factura no puede exceder los 50 caracteres")
	private String invoiceNumber;
}
