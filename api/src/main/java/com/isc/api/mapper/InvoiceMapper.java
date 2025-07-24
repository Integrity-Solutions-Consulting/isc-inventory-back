package com.isc.api.mapper;

import com.isc.api.dto.response.InvoiceResponseDTO;
import com.isc.api.dto.response.InvoiceDetailResponseDTO;
import com.isc.api.entitys.InvoiceEntity;

public class InvoiceMapper {

    public static InvoiceResponseDTO toInvoiceDto(InvoiceEntity entity) {
        if (entity == null) return null;

        return new InvoiceResponseDTO(
            entity.getId(),
            entity.getInvoiceDetailRequest() != null ? entity.getInvoiceDetailRequest().getId() : null,
            entity.getSupplier() != null ? entity.getSupplier().getId() : null,		
            entity.getSupplier() != null ? entity.getSupplier().getBusinessName() : null,
            entity.getInvoiceDate(),
            entity.getInvoiceNumber()
        );
    }

    public static InvoiceDetailResponseDTO toInvoiceDetailDto(InvoiceEntity entity) {
        if (entity == null) return null;

        return new InvoiceDetailResponseDTO(
            entity.getId(),
            entity.getInvoiceDetailRequest().getDescription() != null ? entity.getInvoiceDetailRequest().getDescription() : null,
            entity.getInvoiceDetailRequest().getUnitPrice() != null ? entity.getInvoiceDetailRequest().getUnitPrice() : null,
            entity.getInvoiceDetailRequest().getQuantity() != null ? entity.getInvoiceDetailRequest().getQuantity(): null,
            entity.getInvoiceDetailRequest().getSubtotal() != null ? entity.getInvoiceDetailRequest().getSubtotal() : null,
            entity.getInvoiceDetailRequest().getTax() != null ? entity.getInvoiceDetailRequest().getTax() : null,
            entity.getInvoiceDetailRequest().getDiscount() != null ? entity.getInvoiceDetailRequest().getDiscount() : null,
            entity.getInvoiceDetailRequest().getTotal() != null ? entity.getInvoiceDetailRequest().getTotal() : null,
            entity.getInvoiceDetailRequest() != null ? entity.getInvoiceDetailRequest().getId() : null,
            entity.getSupplier() != null ? entity.getSupplier().getId() : null,	
            entity.getSupplier() != null ? entity.getSupplier().getBusinessName() : null,
            entity.getInvoiceDate(),
            entity.getInvoiceNumber(),		
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
