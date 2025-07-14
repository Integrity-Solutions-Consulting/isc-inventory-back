package com.isc.api.controllers;

import com.isc.api.dto.response.MessageResponseDTO;
import com.isc.dtos.ResponseDto;
import com.isc.api.service.WarrantTypeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warrant-types")
@RequiredArgsConstructor
public class WarrantTypeController {

    private final WarrantTypeService warrantTypeService;


    @PatchMapping("/inactive/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> inactive(@PathVariable Integer id) {
        return ResponseEntity.ok(warrantTypeService.inactive(id));
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<ResponseDto<MessageResponseDTO>> active(@PathVariable Integer id) {
        return ResponseEntity.ok(warrantTypeService.active(id));
    }
}
