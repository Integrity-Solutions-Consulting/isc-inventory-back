package com.isc.mapper;

import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.entitys.InvoiceEntity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceMapper {
	public static InvoiceResponseDTO toInvoiceDto(InvoiceEntity entity) {
        if (entity == null) return null;
        
        return new InvoiceResponseDTO(entity.getId(),
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getId() : null,
            entity.getSupplier() != null ? entity.getSupplier().getId() : null,
            entity.getInvoiceDate(),entity.getInvoiceNumber()
        );
    }
	
    public static InvoiceDetailResponseDTO toInvoiceDetailDto(InvoiceEntity entity) {
        if (entity == null) return null;
        
        return new InvoiceDetailResponseDTO(
            entity.getId(),
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getId() : null,
            entity.getSupplier() != null ? entity.getSupplier().getId() : null,
            entity.getInvoiceDate(),entity.getInvoiceNumber(),
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
