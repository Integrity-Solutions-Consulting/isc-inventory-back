package com.isc.controllers;

import com.isc.dto.request.InvoiceRequestDTO;
import com.isc.dto.response.InvoiceDetailResponseDTO;
import com.isc.dto.response.InvoiceResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.InvoiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<InvoiceDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(invoiceService.getAllDetails());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<InvoiceResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(invoiceService.getSimpleList());
    }


    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(invoiceService.inactive(id));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(invoiceService.active(id));
    }
}
