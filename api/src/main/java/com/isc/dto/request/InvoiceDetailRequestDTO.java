package com.isc.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetailRequestDTO {
	
	private Integer id;
    
	@NotBlank(message = "La descripcion de los detalles de la factura no puede estar vacía")
    @Size(max = 100, message = "La descripcion no puede tener más de 100 caracteres")
	private String description;
    
	@NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio unitario debe ser mayor a 0")
	private BigDecimal unitPrice;
	
	@NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;
    
	@NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El subtotal debe ser mayor a 0")
    private BigDecimal subtotal;
    
	@NotNull(message = "El iva unitario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El iva debe ser mayor a 0")
    private BigDecimal tax;
    
	@NotNull(message = "El descuento es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El descuento debe ser mayor a 0")
    private BigDecimal discount;
    
	@NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    private BigDecimal total;
}
