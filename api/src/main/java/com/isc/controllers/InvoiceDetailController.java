package com.isc.controllers;

import com.isc.dto.request.InvoiceDetailRequestDTO;
import com.isc.dto.response.InvoiceDetailEntityDetailResponseDTO;
import com.isc.dto.response.InvoiceDetailEntityResponseDTO;
import com.isc.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.service.InvoiceDetailService;

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
@RequestMapping("/api/invoice-detail")
@RequiredArgsConstructor
public class InvoiceDetailController {

    private final InvoiceDetailService invoiceDetailService;

    @GetMapping("/details")
    public ResponseEntity<ResponseDto<List<InvoiceDetailEntityDetailResponseDTO>>> getAllDetails() {
        return ResponseEntity.ok(invoiceDetailService.getAllDetails());
    }

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<InvoiceDetailEntityResponseDTO>>> getSimpleList() {
        return ResponseEntity.ok(invoiceDetailService.getSimpleList());
    }


    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(invoiceDetailService.inactive(id));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(invoiceDetailService.active(id));
    }
}
