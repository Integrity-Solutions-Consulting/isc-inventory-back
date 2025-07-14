package com.isc.mapper;

import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.entitys.InvoiceEntity;

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
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getCategory().getId() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getDescription() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getUnitPrice() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getQuantity() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getSubtotal() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getTax() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getDiscount() : null,
            entity.getInvoiceDetail() != null ? entity.getInvoiceDetail().getTotal() : null,
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
