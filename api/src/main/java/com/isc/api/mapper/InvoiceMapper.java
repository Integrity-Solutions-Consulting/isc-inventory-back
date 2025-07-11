package com.isc.api.mapper;

import com.isc.api.dto.response.InvoiceResponseDTO;
import com.isc.api.dto.response.InvoiceDetailResponseDTO;
import com.isc.api.entitys.InvoiceEntity;

public class InvoiceMapper {

    public static InvoiceResponseDTO toInvoiceDto(InvoiceEntity entity) {
        if (entity == null) return null;

        return new InvoiceResponseDTO(
            entity.getId(),
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getId() : null,
            entity.getSupplier() != null ? entity.getSupplier().getId() : null,
            entity.getInvoiceDate(),
            entity.getInvoiceNumber()
        );
    }

    public static InvoiceDetailResponseDTO toInvoiceDetailDto(InvoiceEntity entity) {
        if (entity == null) return null;

        return new InvoiceDetailResponseDTO(
            entity.getId(),
            entity.getInvoiceDetail().getDescription() != null ? entity.getInvoiceDetail().getDescription() : null,
            entity.getInvoiceDetail().getUnitPrice() != null ? entity.getInvoiceDetail().getUnitPrice() : null,
            entity.getInvoiceDetail().getQuantity() != null ? entity.getInvoiceDetail().getQuantity(): null,
            entity.getInvoiceDetail().getSubtotal() != null ? entity.getInvoiceDetail().getSubtotal() : null,
            entity.getInvoiceDetail().getTax() != null ? entity.getInvoiceDetail().getTax() : null,
            entity.getInvoiceDetail().getDiscount() != null ? entity.getInvoiceDetail().getDiscount() : null,
            entity.getInvoiceDetail().getTotal() != null ? entity.getInvoiceDetail().getTotal() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getId() : null,
            entity.getSupplier() != null ? entity.getSupplier().getBusinessName() : null,
            entity.getInvoiceDate(),
            entity.getInvoiceNumber(),
            								
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
