package com.isc.mapper;

import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.entitys.InvoiceDetailEntity;
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

        InvoiceDetailEntity detail = entity.getInvoiceDetail();
        return new InvoiceDetailResponseDTO(
            entity.getId(),
            entity.getInvoiceDetail() != null ? detail.getCategory().getId() : null,
            entity.getInvoiceDetail() != null ? detail.getDescription() : null,
            entity.getInvoiceDetail() != null ? detail.getUnitPrice() : null,
            entity.getInvoiceDetail() != null ? detail.getQuantity() : null,
            entity.getInvoiceDetail() != null ? detail.getSubtotal() : null,
            entity.getInvoiceDetail() != null ? detail.getTax() : null,
            entity.getInvoiceDetail() != null ? detail.getDiscount() : null,
            entity.getInvoiceDetail() != null ? detail.getTotal() : null,
            entity.getStatus(),
            entity.getCreationDate(),
            entity.getModificationDate()
        );
    }
}
