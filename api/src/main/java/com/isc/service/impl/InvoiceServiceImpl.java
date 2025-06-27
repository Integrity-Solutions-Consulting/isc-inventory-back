package com.isc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.isc.dto.request.InvoiceRequestDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.MetadataResponseDto;
import com.isc.dtos.ResponseDto;
import com.isc.entitys.InvoiceDetailEntity;
import com.isc.entitys.InvoiceEntity;
import com.isc.entitys.SupplierEntity;
import com.isc.mapper.InvoiceMapper;
import com.isc.repository.InvoiceDetailRepository;
import com.isc.repository.InvoiceRepository;
import com.isc.repository.SupplierRepository;
import com.isc.service.InvoiceDetailService;
import com.isc.service.InvoiceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;
    private final SupplierRepository supplierRepository;
    
    private final InvoiceDetailService invoiceDetailService;

    @Override
    public ResponseDto<List<InvoiceDetailResponseDTO>> getAllDetails() {
        List<InvoiceDetailResponseDTO> response = invoiceRepository.findAll().stream()
                .map(InvoiceMapper::toInvoiceDetailDto)
                .collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Facturas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public ResponseDto<List<InvoiceResponseDTO>> getSimpleList() {
        List<InvoiceResponseDTO> response = invoiceRepository.findAllByStatusTrue().stream()
                .map(InvoiceMapper::toInvoiceDto)
                .collect(Collectors.toList());
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Facturas activas listadas correctamente");
        return new ResponseDto<>(response, metadata);
    }

    @Override
    public InvoiceEntity save(InvoiceRequestDTO request) {
        SupplierEntity supplier = supplierRepository.findById(request.getSupplier())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        
        
        InvoiceEntity entity = new InvoiceEntity();
        InvoiceDetailEntity detail = invoiceDetailService.save(request.getInvoiceDetail());
        	
        entity.setInvoiceDetail(detail);
        entity.setSupplier(supplier);
        entity.setInvoiceDate(request.getInvoiceDate());
        entity.setInvoiceNumber(request.getInvoiceNumber());

        return entity;
    }

    @Override
    public InvoiceEntity update(InvoiceRequestDTO request, Integer id) {
        InvoiceEntity entity = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
        
        InvoiceDetailEntity detail = invoiceDetailService.update(request.getInvoiceDetail());

        if (!entity.getSupplier().getId().equals(request.getSupplier())) {
            SupplierEntity supplier = supplierRepository.findById(request.getSupplier())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
            entity.setSupplier(supplier);
        }

        entity.setInvoiceDetail(detail);
        entity.setInvoiceDate(request.getInvoiceDate());
        entity.setInvoiceNumber(request.getInvoiceNumber());

        return entity;
    }

    @Override
    public ResponseDto<MessageResponseDTO> inactive(Integer id) {
        int rowsAffected = invoiceRepository.inactive(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo inactivar la factura con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Factura inactivada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }

    @Override
    public ResponseDto<MessageResponseDTO> active(Integer id) {
        int rowsAffected = invoiceRepository.active(id);
        if (rowsAffected == 0) {
            throw new RuntimeException("No se pudo activar la factura con ID: " + id);
        }
        MetadataResponseDto metadata = new MetadataResponseDto(HttpStatus.OK, "Factura activada correctamente");
        return new ResponseDto<>(new MessageResponseDTO("Operación exitosa"), metadata);
    }
}
