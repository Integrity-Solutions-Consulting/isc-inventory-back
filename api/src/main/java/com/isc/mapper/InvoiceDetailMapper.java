package com.isc.mapper;

import com.isc.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.entitys.InvoiceDetailEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceDetailMapper {
	public static InvoiceDetailEntityResponseDTO toInvoiceDetailEntityResponseDTO(InvoiceDetailEntity entity) {
        if (entity == null) 
        	return null;        
        return new InvoiceDetailEntityResponseDTO(
            entity.getId(),
            entity.getCategory() != null ? entity.getCategory().getId() : null,
            entity.getDescription(),
            entity.getUnitPrice(),
            entity.getQuantity(),
            entity.getSubtotal(),
            entity.getTax(),
            entity.getDiscount(),
            entity.getTotal());
		}
	
	public static InvoiceDetailEntityDetailResponseDTO toInvoiceDetailEntityDetailResponseDTO(InvoiceDetailEntity entity) {
        if (entity == null) return null;
        
        return new InvoiceDetailEntityDetailResponseDTO(
            entity.getId(),
            entity.getCategory() != null ? entity.getCategory().getId() : null,
            entity.getDescription(),
            entity.getUnitPrice(),
            entity.getQuantity(),
            entity.getSubtotal(),
            entity.getTax(),
            entity.getDiscount(),
            entity.getTotal(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate());
	}
}
